const express = require('express');
const User = require('../schemas/user');

const router = express.Router();

/* GET users listing. */
router.get('/', (req, res, next) => {
    res.send('<h1>Test /users GET router</h1>');
});


/* POST users/sign-up */
router.post('/sign-up', async (req, res, next) => {
    console.log('#sign-up');
    try {
        const existUser = await User.findOne({ id: req.body.id });
        if (!existUser) {
            const newUser = await User.create({
                id: req.body.id,
                password: req.body.password,
                name: req.body.name,
            })
            console.log(`#success ${req.body.id}`);
            res.status(201).json({ 'sign-up': true })
        } else {
            console.log(`#fail ${req.body.id}`);
            res.status(200).json({ 'sign-up': false });
        }
    } catch (err) {
        console.log(err);
        next(err);
    }
});


/* POST users/sign-in */
router.post('/sign-in', async (req, res, next) => {
    console.log('#sign-in');
    try {
        const signInUser = await User.findOne({ id: req.body.id });
        if (!signInUser) {
            console.log(`#fail ${req.body.id}`);
            res.status(200).json({ 'sign-in': false });
        }
        else if (signInUser.password !== req.body.password) {
                console.log(`#fail ${req.body.id}`);
                res.status(400).json({ 'sign-in': false });
        } else {
            console.log(`#success' ${req.body.id}`);
            res.json({
                'sign-in': true,
                'name': signInUser.name,
                'content': signInUser.content,
            });
        }
    } catch (err) {
        console.log(err);
        next(err);
    }
});

module.exports = router;
