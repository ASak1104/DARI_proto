const mongoose = require('mongoose');

const { Schema } = mongoose;
const { getDate } = require('./index');
const interestSchema = new Schema({
    name: {
        type: String,
        required: true,
        unique: true,
        index: true,
    },
    userCount: {
        type: Number,
        default: 0,
    },
    createdAt: {
        type: String,
        default: getDate,
    },
});

module.exports = mongoose.model('Interest', interestSchema);
