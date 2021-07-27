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
    max: {
        type: Number,
        required: true,
        min: 2,
        default: 2,
    },
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
        return User.findById(otherUser.user, 'name -_id').lean().then((obj) => obj.name);
    }));
    channel.userNameTitle = otherUserNames.join(', ');
};

module.exports = mongoose.model('Channel', channelSchema);