const express = require('express');
const passport = require('passport');
const { isSignedIn, isNotSignedIn } = require('./middlewares');
const User = require('../schemas/user');
const Interest = require('../schemas/interest');
const UserToInterest = require('../schemas/userToInterest');

const router = express.Router();


/* POST /profile/:id page */
// need to add MW
router.post('/:id', async (req, res, next) => {
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
        res.status(201).json({ updated: true });
    } catch (err) {
        console.log(err);
        return next(err);
    }
});


module.exports = router;