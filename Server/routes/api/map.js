const express = require('express');
const { verifyToken } = require('../middlewares');
const User = require('../../schemas/user');
const Interest = require('../../schemas/interest');
const UserToInterest = require('../../schemas/userToInterest');

const router = express.Router();


/* GET api/map page */
router.get('/', verifyToken, async (req, res, next) => {
    try {
        const user = await User.findById(req.decoded._id, 'interests location').lean();

        const getUserWithOthers = async () => {
            const interests = await Interest.find({ _id: { $in: user.interests } }, 'name').lean();
            await Promise.all(interests.map(async (interest) => {
                const otherUsers = await User.find({
                    _id: { $ne: user._id },
                    interests: interest._id,
                    location: {
                        $nearSphere: {
                            $geometry : {
                                type: "Point",
                                coordinates: user.location.coordinates,
                            },
                            $maxDistance : 3_000_000,
                        }
                    },
                }, 'userId userName introduce location interests -_id').lean();
                await Promise.all(otherUsers.map(async (otherUser) => {
                    otherUser.interests = await Promise.all(otherUser.interests.map(async (_id) => {
                        return await Interest.findById(_id, 'name -_id').lean()
                            .then((obj) => obj.name);
                    }));
                }));
                interest.otherUsers = otherUsers;
                delete interest._id;
            }));
            return interests;
        };

        res.json({
            interests: await getUserWithOthers(),
        })
    } catch (err) {
        console.log(err);
        return next(err);
    }
});


module.exports = router;