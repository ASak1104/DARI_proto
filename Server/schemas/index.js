const mongoose = require('mongoose');
const moment = require('moment');
require('moment-timezone')

const DB_URL = process.env.DB_URL;
moment.tz.setDefault('Asia/Seoul');

const getDate = () => {
    return moment().format('YYYY-MM-DD HH:mm:ss:ms');
};

const connect = () => {
    if (process.env.NODE_ENV !== 'production') {
        mongoose.set('debug', true);
    }
    mongoose.connect(DB_URL, {
        dbName: 'test',
        useCreateIndex: true,
        useNewUrlParser: true,
        useUnifiedTopology: true,
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


module.exports = { getDate, connect };