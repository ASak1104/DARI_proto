const express = require('express');
const { getDate, verifyToken, imageDirs, upload } = require('../middlewares');
const User = require('../../schemas/user');
const Channel = require('../../schemas/channel');
const Message = require('../../schemas/message');
const UserToChannel = require('../../schemas/userToChannel');
const path = require('path');
const fs = require('fs');

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


/* GET api/messenger/message page */
router.get('/message', verifyToken, async (req, res, next) => {
    try {
        const { channel_id } = req.body;
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


/* POST api/messenger/message page */
router.post('/message', verifyToken, async (req, res, next) => {
    try {
        const { channel_id, content } = req.body;
        const createdAt = getDate();

        const [ , { users } ] = await Promise.all([
            Message.create({
                channel: channel_id,
                user: req.decoded._id,
                content,
                createdAt,
            }),
            Channel.findByIdAndUpdate(channel_id, {
                lastMessage: content, updatedAt: createdAt,
            }).lean(),
        ]);

        const io = req.app.get('io');

        io.to(users.map(String)).emit('newMessage', {
            channel_id,
            userId: req.decoded.userId,
            userName: req.decoded.userName,
            content,
            createdAt,
        });

        /*
        io.to(channel_id).emit('newMessage', {
            userId: req.decoded.userId,
            userName: req.decoded.userName,
            content,
            createdAt,
        });
        */

        res.status(201).json({ status: 201 });
    } catch (err) {
        console.log(err);
        return next(err);
    }
});


/* GET api/messenger/image/:file page */
router.get('/image/:file', verifyToken, async (req, res, next) => {
    try {
        const file = path.join(__dirname, `../../${ imageDirs.messenger }/${ req.params.file }`);
        fs.access(file, (err) => {
            if (err) {
                // console.error(err);
                res.status(404).send();
            } else {
                res.sendFile(path.join(__dirname, `../../${ imageDirs.messenger }/${ req.params.file }`));
            }
        });
    } catch(err) {
        console.log(err);
        return next(err);
    }
});


/* POST api/messenger/image page */
router.post('/image', verifyToken, upload(imageDirs.messenger, getDate()).single('image'), async (req, res, next) => {
    try {
        const { channel_id } = req.body;
        const createdAt = req.decoded.imageCreatedAt;
        const image = req.file.filename;

        const [ , { users } ] = await Promise.all([
            Message.create({
                channel: channel_id,
                user: req.decoded._id,
                image,
                createdAt,
            }),
            Channel.findByIdAndUpdate(channel_id, {
                lastMessage: '사진을 보냈습니다.', updatedAt: createdAt,
            }).lean(),
        ]);

        const io = req.app.get('io');

        io.to(users.map(String)).emit('newMessage', {
            channel_id,
            userId: req.decoded.userId,
            userName: req.decoded.userName,
            image,
            createdAt,
        });

        res.status(201).json({ status: 201 });
    } catch(err) {
        console.log(err);
        return next(err);
    }
});


module.exports = router;