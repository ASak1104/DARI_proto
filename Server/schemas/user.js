const mongoose = require('mongoose');

const { Schema } = mongoose;
const { getDate } = require('../routes/middlewares');
const userSchema = new Schema({
    userId: {
        type: String,
        required: true,
        unique: true,
        index: true,
    },
    password: {
        type: String,
        required: true,
    },
    name: {
        type: String,
        required: true,
    },
    isOnline: {
        type: Boolean,
        default: false,
    },
    introduce: String,
    latitude: Number,
    longitude: Number,
    createdAt: {
        type: String,
        default: getDate,
    },
});

module.exports = mongoose.model('User', userSchema);
