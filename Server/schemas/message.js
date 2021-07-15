const mongoose = require('mongoose');

const { Schema } = mongoose;
const { getDate } = require('./index');
const { Types: { ObjectId } } = Schema;
const messageSchema = new Schema({
    user:{
        type: ObjectId,
        required: true,
        ref: 'User',
    },
    channel: {
        type: ObjectId,
        required: true,
        ref: 'Channel',
    },
    content: String,
    image: String,
    createdAt: {
        type: Date,
        default: getDate,
    },
});

module.exports = mongoose.model('Message', messageSchema);