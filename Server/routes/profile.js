const express = require('express');
const { isSignedIn, isNotSignedIn } = require('./middlewares');
const User = require('../schemas/user');
const Interest = require('../schemas/interest');
const UserToInterest = require('../schemas/userToInterest');

const router = express.Router();


/* POST user/:id/profile page */
router.post('/:id/profile', async (req, res, next) => {
    const { introduce, latitude, longitude, interests } = req.body;
    try {
        const user_id = await User.findOne( { userId: req.params.id }, '_id').lean();

        const updateUser = async () => {
            await User.findByIdAndUpdate(user_id, { introduce, latitude, longitude });
        };

        const createUserToInterest = async () => {
            await Promise.all(interests.map(async (item) => {
                const interest = await Interest.findOne({ name: item }, '_id').lean();
                await UserToInterest.create({
                    user: user_id,
                    interest: interest._id,
                })
            }));
        };

        await Promise.all([updateUser(), createUserToInterest()]);
        res.status(201).json({ created: true });
    } catch (err) {
        console.log(err);
        return next(err);
    }
});


/* PUT user/:id/profile/interest page */
router.put('/:id/profile/interest', async (req, res, next) => {
    const { interests } = req.body;
    try {
        const _id = await User.findOne( { userId: req.params.id }, '_id').lean()
            .then((user) => user._id);
        const newInterests = await Promise.all(interests.map(async (name) => {
            return await Interest.findOne({ name }, '_id').lean()
                .then((obj) => obj._id);
        }));

        const deleteOldUserInterest = async () => {
            await UserToInterest.deleteMany({ user: _id ,interest: { $nin: newInterests } });
        };

        const createNewUserInterest = async () => {
            await Promise.all(newInterests.map(async (interest) => {
                await UserToInterest.findOneAndUpdate({ user: _id, interest }, {}, { upsert: true });
            }));
        };

        await Promise.all([deleteOldUserInterest(), createNewUserInterest()]);

        res.status(202).json({ updated: true });
    } catch (err) {
        console.log(err);
        return next(err);
    }
});


/*
/!* POST user/:id/profile/interest page *!/
// need to add MW
router.post('/:id/profile/interest', async (req, res, next) => {
    const { interests } = req.body;
    try {
        const user = await User.findOne( { userId: req.params.id }, '_id').lean();
        await Promise.all(interests.map(async (item) => {
            const interest = await Interest.findOne({ name: item }, '_id').lean();
            await UserToInterest.create({
                user: user._id,
                interest: interest._id,
            })
        }));
        res.status(201).json({ created: true });
    } catch (err) {
        console.log(err);
        return next(err);
    }
});
*/


module.exports = router;