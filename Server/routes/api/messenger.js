const express = require('express');
const { verifyToken } = require('../middlewares');
const User = require('../../schemas/user');
const Channel = require('../../schemas/channel');
const Message = require('../../schemas/message');
const UserToChannel = require('../../schemas/userToChannel');

const router = express.Router();


/* GET api/messenger/channel page */
router.get('/channel', verifyToken, async (req, res, next) => {
    try {
        const user_id = req.decoded._id;
        const channels_ids = await UserToChannel.find({ user: user_id }, 'channel -_id').lean()
            .then((objs) => objs.map((obj) => obj.channel));
        const channels = await Channel.find({ _id: { $in: channels_ids } }, 'lastMessage updatedAt').sort({ updatedAt: -1 }).lean();
        await Promise.all(channels.map(async (channel) => {
            await Channel.addUserNameTitle(channel, user_id);
        }));
        res.json({
            channels
        });
    } catch (err) {
        console.log(err);
        return next(err);
    }
});


/* POST api/messenger/channel page */
router.post('/channel', verifyToken, async (req, res, next) => {
    try {
        const { _id, userId } = req.decoded;
        const { otherUserId } = req.body;
        const otherUser_id = await User.findOne({ userId: otherUserId }, '_id')
            .then((obj) => obj._id);

        const title = req.body.title ? (req.body.title): `${ userId }, ${ otherUserId }`;
        const newChannel = await Channel.create({ title, users: [ _id, otherUser_id ] });

        // when otherUserName -> otherUserNames, [user_id, ...otherUser_ids]
        await Promise.all([_id, otherUser_id].map(async (user) => {
            await UserToChannel.create({ user, channel: newChannel._id });
        }));

        res.status(201).json({
            status: 201,
            channel_id: newChannel._id,
        });
    } catch (err) {
        console.log(err);
        return next(err);
    }
});


/* GET api/messenger/:channel_id page */
router.get('/:channel_id', verifyToken, async (req, res, next) => {
    try {
        const { channel_id } = req.params;
        const messages = await Message.find({ channel: { $eq: channel_id } }, 'user content image createdAt -_id').sort({ createdAt: 1 }).lean();
        await Promise.all(messages.map(async (message) => {
            await Message.addUserIdAndName(message);
            delete message.user;
        }));
        res.json({ messages });
    } catch(err) {
        console.log(err);
        return next(err);
    }
});


/* POST api/messenger/:channel_id page */
router.post('/:channel_id', verifyToken, async (req, res, next) => {
    try {
        const { channel_id } = req.params;
        const { content } = req.body;

        const newMessage = await Message.create({
            channel: channel_id,
            user: req.decoded._id,
            content,
        });

        await Channel.findByIdAndUpdate(channel_id, { lastMessage: content, updatedAt: newMessage.createdAt });

        const obj = {
            userId: req.decoded.userId,
            userName: req.decoded.userName,
            content,
            createdAt: newMessage.createdAt,
        };

        req.app.get('io').to(channel_id).emit('newMessage', obj);
        res.status(201).json({ status: 201 });
    } catch (err) {
        console.log(err);
        return next(err);
    }
});


module.exports = router;