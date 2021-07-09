const mongoose = require('mongoose');

const DB_URL = process.env.DB_URL;

const connect = () => {
    if (process.env.NODE_ENV !== 'production') {
        mongoose.set('debug', true);
    }
    mongoose.connect(DB_URL, {
        dbName: 'test',
        useCreateIndex: true,
        useNewUrlParser: true,
        useUnifiedTopology: true,
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