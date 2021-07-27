const express = require('express');
const { verifyToken } = require('../middlewares');
const User = require('../../schemas/user');
const Interest = require('../../schemas/interest');
const UserToInterest = require('../../schemas/userToInterest');

const router = express.Router();


/* GET user/profile page */
router.get('/', verifyToken, async (req, res, next) => {
    try {
        const user = await User.findById(req.decoded._id, 'userId introduce name location').lean();
        await User.addInterests(user);
        delete user._id;
        res.json(user);
    } catch (err) {
        console.log(err);
        return next(err);
    }
});


/* POST user/profile page */
router.post('/', verifyToken, async (req, res, next) => {
    try {
        const user_id = req.decoded._id;
        const { introduce, interests } = req.body;
        const interests_ids = await Interest.find({ name: { $in: interests } }, '_id').lean()
            .then((objs) => objs.map((obj) => obj._id))

        const updateUser = async () => {
            await User.findByIdAndUpdate(user_id, { introduce });
        };

        const createUserToInterest = async () => {
            await Promise.all(interests_ids.map(async (interest) => {
                await UserToInterest.create({ user: user_id, interest });
            }));
        };

        const updateUserCount = async () => {
             await Interest.updateMany({ _id: { $in: interests_ids } }, { $inc: { userCount: +1 } });
        };

        await Promise.all([updateUser(), createUserToInterest(), updateUserCount()]);

        res.status(201).json({ created: true });
    } catch (err) {
        console.log(err);
        return next(err);
    }
});


/* PUT user/profile page */
router.put('/', verifyToken, async (req, res, next) => {
    try {
        const user_id = req.decoded._id;
        const { name, introduce, interests } = req.body;
        const preInterests = await UserToInterest.find({ user: user_id }, 'interest -_id').lean()
            .then((objs) => objs.map((obj) => obj.interest));

        const updateUser = async () => {
            await User.findByIdAndUpdate(user_id, { name, introduce });
        };

        const deleteOldUserInterest = async () => {
            const oldInterests = await Interest.find({ name: { $nin: interests }, _id: { $in: preInterests } }, '_id').lean()
                .then((objs) => objs.map((obj) => obj._id));
            await Promise.all([
                UserToInterest.deleteMany({ user: user_id, interest: { $in: oldInterests } }),
                Interest.updateMany({ _id: { $in: oldInterests } }, { $inc: { userCount: -1 } }),
            ]);
        };

        const createNewUserInterest = async () => {
            const newInterests = await Interest.find({ name: { $in: interests }, _id: { $nin: preInterests } }, '_id').lean()
                .then((objs) => objs.map((obj) => obj._id));
            await Promise.all([
                Promise.all(newInterests.map(async (interest) => {
                    await UserToInterest.create({ user: user_id, interest });
                })),
                Interest.updateMany({ _id: { $in: newInterests } }, { $inc: { userCount: +1 } }),
            ]);
        };

        await Promise.all([updateUser(), deleteOldUserInterest(), createNewUserInterest()]);

        res.status(202).json({ updated: true });
    } catch (err) {
        console.log(err);
        return next(err);
    }
});


module.exports = router;
