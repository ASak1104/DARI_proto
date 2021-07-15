const mongoose = require('mongoose');

const { Schema } = mongoose;
const { getDate } = require('./index');
const { Types: { ObjectId } } = Schema;
const channelSchema = new Schema({
    title: {
        type: String,
        required: true,
        default: 'test',
    },
    max: {
        type: Number,
        required: true,
        min: 2,
        default: 2,
    },
    owner: {
        type: ObjectId,
        required: true,
        default: null,
        ref: 'User',
    },
    createdAt: {
        type: String,
        default: getDate,
    },
});

module.exports = mongoose.model('Channel', channelSchema);