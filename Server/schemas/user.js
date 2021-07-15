const mongoose = require('mongoose');

const { Schema } = mongoose;
const { Types: { ObjectId } } = Schema;
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
        type: Date,
        default: Date.now() + (60 * 60 * 1000 * 9),
    },
});

module.exports = mongoose.model('User', userSchema);
