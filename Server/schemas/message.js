const mongoose = require('mongoose');

const { Schema } = mongoose;
const { getDate } = require('../routes/middlewares');
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
        type: String,
        default: getDate,
    },
});

module.exports = mongoose.model('Message', messageSchema);