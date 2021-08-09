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
            await Promise.all([
                Channel.addUserNameTitle(channel, user_id),
                Channel.addOtherUserIds(channel, user_id),
            ]);
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

        // when otherUser_id -> otherUser_ids, [_id, ...otherUser_ids]
        const users = [ _id, otherUser_id ].sort();
        const existChannel = await Channel.findOne({ users: { $eq: users } }, '_id').lean()

        if (existChannel) {
            res.status(202).json({
                status: 202,
                channel_id: existChannel._id,
            });
        } else {
            const title = req.body.title ? (req.body.title): `${[ userId, otherUserId ].sort().join(', ')}`;
            const newChannel = await Channel.create({ title, users });

            await Promise.all(users.map(async (user) => {
                await UserToChannel.create({ user, channel: newChannel._id });
            }));

            res.status(201).json({
                status: 201,
                channel_id: newChannel._id,
            });
        }
    } catch (err) {
        console.log(err);
        return next(err);
    }
});


module.exports = router;