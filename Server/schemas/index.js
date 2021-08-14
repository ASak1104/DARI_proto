const mongoose = require('mongoose');
const { DB_URL, DB_NAME } = process.env;

const connect = () => {
    if (process.env.NODE_ENV !== 'production') {
        mongoose.set('debug', true);
    }
    mongoose.connect(DB_URL, {
        dbName: DB_NAME,
        useCreateIndex: true,
        useNewUrlParser: true,
        useUnifiedTopology: true,
        useFindAndModify: false,
    }, (err) => {
        if (err) {
            console.log('Fail to connect MongoDB', err);
        } else {
            console.log('Success to connect MongoDB');
        }
    });
};
mongoose.connection.on('error', (err) => {
    console.error('Error on MongoDB connection', err);
});
mongoose.connection.on('disconnected', () => {
    console.error('Disconnect from MongoDB. Try to connect.');
    connect();
});


module.exports = connect;