const express = require('express');
const { isSignedIn, isNotSignedIn } = require('../middlewares');
const User = require('../../schemas/user');
const Interest = require('../../schemas/interest');
const UserToInterest = require('../../schemas/userToInterest');

const router = express.Router();


/* GET user/:id/profile page */
router.get('/:id/profile', async (req, res, next) => {
    try {
        const user = await User.findOne({ userId: req.params.id }, 'userId name introduce latitude longitude').lean();
        await User.addInterests(user);
        delete user._id;
        res.json(user);
    } catch (err) {
        console.log(err);
        return next(err);
    }
});


/* POST user/:id/profile page */
router.post('/:id/profile', async (req, res, next) => {
    const { introduce, latitude, longitude, interests } = req.body;
    try {
        const [ user_id, interests_ids ] = await Promise.all([
            User.findOne( { userId: req.params.id }, '_id').lean(),
            Interest.find({ name: { $in: interests } }, '_id').lean()
                .then((objs) => objs.map((obj) => obj._id)),
        ]);

        const updateUser = async () => {
            await User.findByIdAndUpdate(user_id, { introduce, latitude, longitude });
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


/* PUT user/:id/profile page */
router.put('/:id/profile', async (req, res, next) => {
    const { name, introduce, interests } = req.body;
    console.log(req.body)
    try {
        const user_id = await User.findOne( { userId: req.params.id }, '_id').lean()
            .then((user) => user._id);
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
