const express = require('express');
const logger = require('morgan');
const dotenv = require('dotenv');
const path = require('path');
const passport = require('passport');

dotenv.config();
const connect = require('./schemas');
const indexRouter = require('./routes');
const userRouter = require('./routes/user');
const apiRouter = require('./routes/api');
const adminRouter = require('./routes/admin');
const passportConfig = require('./passport');
const webSocket = require('./socket');

const app = express();
app.set('port', process.env.PORT || 3000);
passportConfig();
app.use(passport.initialize());

app.use(logger('dev'));
app.use(express.static(path.join(__dirname, '/public')));
app.use(express.json());
app.use(express.urlencoded({ extended: true }));
connect();

app.use('/', indexRouter);
app.use('/user', userRouter);
app.use('/api', apiRouter);
app.use(process.env.ADMIN_PAGE, adminRouter);

app.use((req, res, next) => {
    const error =  new Error(`Not exist ${ req.method } ${ req.url } router`);
    error.status = 404;
    next(error);
});

app.use((err, req, res, next) => {
    console.log(err);
    res.locals.message = err.message;
    res.locals.error = process.env.NODE_ENV !== 'production' ? err : {};
    res.status(err.status || 500).send();
});

const server = app.listen(app.get('port'), () => {
    console.log(`Listening localhost:${ app.get('port') }`);
});

webSocket(server, app);
