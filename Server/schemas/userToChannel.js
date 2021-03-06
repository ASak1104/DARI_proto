const mongoose = require('mongoose');

const { Schema } = mongoose;
const { Types: { ObjectId } } = Schema;
const userToChannelSchema = new Schema({
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
});
userToChannelSchema.index({ user: 1, channel: 1 }, { unique: true });


module.exports = mongoose.model('UserToChannel', userToChannelSchema);