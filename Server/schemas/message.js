const mongoose = require('mongoose');
const User = require('../schemas/user');

const { Schema } = mongoose;
const { getDate } = require('../routes/middlewares');
const { Types: { ObjectId } } = Schema;
const messageSchema = new Schema({
    user: {
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

messageSchema.statics.addUserIdAndName = async (message) => {
    const user = await User.findById(message.user, 'userId name -_id').lean();
    message.userId = user.userId;
    message.userName = user.name;
};


module.exports = mongoose.model('Message', messageSchema);