const express = require('express');
const passport = require('passport');
const { isSignedIn, isNotSignedIn } = require('./middlewares');
const User = require('../schemas/user');
const Interest = require('../schemas/interest');
const UserToInterest = require('../schemas/userToInterest');

const router = express.Router();


/* GET /map/id page */
// apply isSignedIn MW later
router.get('/:id', async (req, res, next) => {
    try {
        const user = await User.findOne( { userId: req.params.id }, '_id').lean();
        const userWithInterest = async (user) => {
            const interestIds = await UserToInterest.find({ user: user._id }, 'interest -_id').lean();
            user.interests = await Promise.all(interestIds.map(async (item) => {
                return Interest.findById(item.interest, 'name -_id').lean().then((obj) => obj.name);
            }));
            delete user._id;
        };

        const getUserWithOthers = async (_id) => {
            const userInterestIds = await UserToInterest.find({ user: _id }, 'interest').lean();
            return await Promise.all(userInterestIds.map(async (item) => {
                const interest = await Interest.findById(item.interest, 'name').lean();
                const otherUserIds = await UserToInterest.find({ _id: { $ne: item._id }, interest: interest._id}, 'user -_id' ).lean()
                    .then((objs) => objs.map((obj) => obj.user));
                delete interest._id;
                interest.otherUsers = await Promise.all(otherUserIds.map(async (o_id) => {
                    const otherUser = await User.findById(o_id, 'userId name latitude longitude').lean();
                    await userWithInterest(otherUser);
                    return otherUser;
                }));
                return interest;
            }))
        };

        res.json({
            interests: await getUserWithOthers(user._id),
        });
    } catch (err) {
        console.log(err);
        return next(err);
    }
});


module.exports = router;