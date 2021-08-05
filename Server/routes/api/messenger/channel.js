const express = require('express');
const { verifyToken } = require('../../middlewares');
const User = require('../../../schemas/user');
const Channel = require('../../../schemas/channel');
const UserToChannel = require('../../../schemas/userToChannel');

const router = express.Router();


/* GET api/messenger/channel page */
router.get('/', verifyToken, async (req, res, next) => {
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
router.post('/', verifyToken, async (req, res, next) => {
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


module.exports = router;