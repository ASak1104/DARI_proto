const express = require('express');
const passport = require('passport');
const { isSignedIn, isNotSignedIn } = require('./middlewares');
const User = require('../schemas/user');
const Interest = require('../schemas/interest');
const UserToInterest = require('../schemas/userToInterest');

const router = express.Router();


/* GET /map/id */
// need to id -> _id
// need to make MW finding interest DB using _id
// apply isSignedIn MW later
router.get('/:id', async (req, res, next) => {
    try {
        const user = await User.findOne( { id: req.params.id }).lean();
        const userInterestIds = await UserToInterest.find({ user: user._id }, 'interest').lean();

        const getUserInterests = async () => {
            return await Promise.all(userInterestIds.map(async (item) => {
                const interest = await Interest.findById(item.interest, 'name -_id').lean()
                return interest.name;
            }))
        };

        const getOtherUsers = async () => {
            const objs2D = await Promise.all(userInterestIds.map(async (item) => {
                return UserToInterest.find({ _id: { $ne: item._id },  interest: item.interest }, 'user -_id').lean()
            }));
            const ids = await Promise.all(objs2D.map(async (items) => {
                return items.map((item) => item.user)
            })).then((arr2D) => [...new Set(arr2D.flat())]);
            return Promise.all(ids.map(async (id) => User.findById(id)));
        };

        const [userInterests, otherUsers] = await Promise.all([getUserInterests(), getOtherUsers()]);

        res.json({
            latitude: user.latitude,
            longitude: user.longitude,
            interests: userInterests,
            otherUsers: otherUsers,
        });
    } catch (err) {
        console.log(err);
        return next(err);
    }
});


module.exports = router;