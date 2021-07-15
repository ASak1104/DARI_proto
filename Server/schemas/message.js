const mongoose = require('mongoose');

const { Schema } = mongoose;
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
        default: Date.now() + (60 * 60 * 1000 * 9),
    },
});

module.exports = mongoose.model('Message', messageSchema);