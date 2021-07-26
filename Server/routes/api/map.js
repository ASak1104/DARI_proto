const express = require('express');
const passport = require('passport');
const { isSignedIn, isNotSignedIn } = require('../middlewares');
const User = require('../../schemas/user');
const Interest = require('../../schemas/interest');
const UserToInterest = require('../../schemas/userToInterest');

const router = express.Router();


/* GET api/map/:id page */
// apply isSignedIn MW later
router.get('/:id', async (req, res, next) => {
    try {
        const { _id, location } = await User.findOne( { userId: req.params.id }, '_id location').lean();

        const getUserWithOthers = async () => {
            const userInterestIds = await UserToInterest.find({ user: _id }, 'interest').lean();
            return await Promise.all(userInterestIds.map(async (item) => {
                const interest = await Interest.findById(item.interest, 'name userCount').lean();
                const otherUserIds = await UserToInterest.find({ _id: { $ne: item._id }, interest: interest._id}, 'user -_id' ).lean()
                    .then((objs) => objs.map((obj) => obj.user));
                delete interest._id;
                interest.otherUsers = await Promise.all(otherUserIds.map(async (o_id) => {
                    const otherUser = await User.findOne({
                        _id: o_id,
                        location: {
                            $nearSphere: {
                                $geometry : {
                                    type: "Point",
                                    coordinates: location.coordinates
                                },
                                $maxDistance : 3_000,
                            }
                        }
                    }, 'userId name introduce location').lean();
                    if (otherUser) {
                        await User.addInterests(otherUser);
                        delete otherUser._id;
                    }
                    return otherUser;
                }));
                return interest;
            }));
        };

        res.json({
            interests: await getUserWithOthers(),
        });
    } catch (err) {
        console.log(err);
        return next(err);
    }
});


module.exports = router;