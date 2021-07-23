const express = require('express');
const passport = require('passport');
const bcrypt = require('bcrypt');
const { isSignedIn, isNotSignedIn } = require('./middlewares');
const User = require('../schemas/user');

const router = express.Router();

/* GET /auth listing. */
router.get('/', (req, res, next) => {
    res.send('<h1>Test /users GET router</h1>');
});


/* POST /auth/sign-up */
router.post('/sign-up', isNotSignedIn, async (req, res, next) => {
    const { id, password, name } = req.body;
    try {
        const exUser = await User.findOne({ userId: id }, '_id').lean();
        if (exUser) {
            return res.status(204).json({ 'isSignedUp': false });
        }
        const hash = await bcrypt.hash(password, 14);
        await User.create({
            userId: id,
            password: hash,
            name,
        });
        return res.status(201).json({ 'isSignedUp': true });
    } catch (err) {
        console.log(err);
        return next(err);
    }
});


/* POST /auth/sign-in */
router.post('/sign-in', isNotSignedIn, (req, res, next) => {
    console.log(`#sign-in ${req.body.id}`);
    passport.authenticate('local', (authError, user, info) => {
        if (authError) {
            console.error(authError);
            return next(authError);
        }
        if (!user) {
            return res.json({
                isSignedIn: false,
                status: info.status,
            });
        }
        return req.login(user, (loginError) => {
            if (loginError) {
                console.log(loginError);
                return next(loginError);
            }
            req.session.userId = req.body.id;
            return res.json({
                isSignedIn: true,
                status: info.status,
            });
        });
    })(req, res, next);
});


/* GET /auth/sign-out */
router.get('/sign-out', isSignedIn, (req, res) => {
    req.logout();
    req.session.destroy();
    res.json({ 'isSignedOut': true });
});


module.exports = router;
