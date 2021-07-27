const express = require('express');
const passport = require('passport');
const bcrypt = require('bcrypt');
const jwt = require('jsonwebtoken');
const { verifyToken } = require('../middlewares');
const User = require('../../schemas/user');

const router = express.Router();


/* GET api/auth/token page */
router.get('/token', verifyToken, (req, res, next) => {
    res.json({ status: 200, message: 'Success to authorize token' });
});


/* POST api/auth/sign-up page */
router.post('/sign-up', async (req, res, next) => {
    const { userId, password, name } = req.body;
    try {
        const exUser = await User.findOne({ userId }, '_id').lean();
        if (exUser) {
            return res.status(204).json({ 'isSignedUp': false });
        }
        const hash = await bcrypt.hash(password, 14);
        await User.create({
            userId,
            password: hash,
            name,
        });
        return res.status(201).json({ 'isSignedUp': true });
    } catch (err) {
        console.log(err);
        return next(err);
    }
});


/* POST api/auth/sign-in page */
router.post('/sign-in', (req, res, next) => {
    console.log(`#sign-in ${req.body.userId}`);
    passport.authenticate('local', (authError, user, info) => {
        if (authError) {
            console.error(authError);
            return next(authError);
        }
        if (!user) {
            return res.json({
                status: info.status,
                message: info.message,
            });
        }
        return req.login(user, (loginError) => {
            if (loginError) {
                console.log(loginError);
                return next(loginError);
            }
            const token = jwt.sign({
                _id: user._id,
                userId: user.userId,
                name: user.name,
            }, process.env.JWT_SECRET, {
                expiresIn: '1d', // 1 day
                issuer: 'DARI',
            });
            return res.status(201).json({
                status: info.status,
                message: info.message,
                token,
            });
        });
    })(req, res, next);
});


/* GET api/auth/sign-out page */
router.get('/sign-out', verifyToken, (req, res) => {
    req.logout();
    res.json({ 'isSignedOut': true });
});


module.exports = router;
