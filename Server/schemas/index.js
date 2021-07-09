const mongoose = require('mongoose');

mongoose.set('useCreateIndex', true)
const MDB_URL = `mongodb://${process.env.MDB_ID}:${process.env.MDB_PASSWORD}@localhost:27017/admin`;

const connect = () => {
    if (process.env.NODE_ENV !== 'production') {
        mongoose.set('debug', true);
    }
    mongoose.connect("mongodb://localhost:27017/test", {
        "user": process.env.MDB_ID,
        "pass": process.env.MDB_PASSWORD,
        "useMongoClient": true
    });
};
mongoose.connection.on('error', (err) => {
    console.error('몽고디비 연결 에러', err);
});
mongoose.connection.on('disconnected', () => {
    console.error('몽고디비 연결이 끊어졌습니다. 연결을 재시도합니다.');
    connect();
});

module.exports = connect;