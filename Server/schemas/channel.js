const mongoose = require('mongoose');
const User = require('./user');
const UserToChannel = require('./userToChannel');

const { Schema } = mongoose;
const { getDate } = require('../routes/middlewares');
const { Types: { ObjectId } } = Schema;
const channelSchema = new Schema({
    title: {
        type: String,
        required: true,
        default: 'test',
    },
    users: [
        {
            type: ObjectId,
            ref: 'User',
            required: true,
        }
    ],
    lastMessage: String,
    createdAt: {
        type: String,
        default: getDate,
    },
    updatedAt: {
        type: String,
        default: getDate,
    },
});

channelSchema.statics.addUserNameTitle = async (channel, user_id) => {
    const otherUsers = await UserToChannel.find({ channel: channel._id, user: { $ne: user_id } }, 'user -_id').lean();
    const otherUserNames = await Promise.all(otherUsers.map(async (otherUser) => {
        return User.findById(otherUser.user, 'userName -_id').lean().then((obj) => obj.userName);
    }));
    channel.userNameTitle = otherUserNames.join(', ');
};

module.exports = mongoose.model('Channel', channelSchema);