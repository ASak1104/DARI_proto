const express = require('express');
const { getDate, verifyToken } = require('../../middlewares');
const Channel = require('../../../schemas/channel');
const Message = require('../../../schemas/message');

const router = express.Router();


/* GET api/messenger/message/:channel_id page */
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


/* POST api/messenger/message page */
router.post('/', verifyToken, async (req, res, next) => {
    try {
        const { channel_id, content } = req.body;

        if (content.trim().length) {
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

            res.status(201).json({ status: 201 });
        } else {
            res.status(400).json({ status: 400 });
        }
    } catch (err) {
        console.log(err);
        return next(err);
    }
});


module.exports = router;