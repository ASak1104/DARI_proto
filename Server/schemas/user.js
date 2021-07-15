const mongoose = require('mongoose');

const { Schema } = mongoose;
const { getDate } = require('./index');
const userSchema = new Schema({
    id: {
        type: String,
        required: true,
        unique: true,
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
    comment: String,
    latitude: Number,
    longitude: Number,
    createdAt: {
        type: String,
        default: getDate,
    },
});

module.exports = mongoose.model('User', userSchema);
