const express = require('express');
const { verifyToken } = require('../middlewares');
const User = require('../../schemas/user');

const router = express.Router();


/* POST user/location page */
router.post('/', verifyToken, async (req, res, next) => {
    try {
        const { location } = req.body;
        await User.findOneAndUpdate({ _id: req.decoded._id }, { location });
        res.status(201).json({ status: 201 });
    } catch (err) {
        console.log(err);
        return next(err);
    }
});


/* PUT user/location page */
router.put('/', verifyToken, async (req, res, next) => {
    try {
        const { location } = req.body;
        await User.findOneAndUpdate({ _id: req.decoded._id }, { location });
        res.status(202).json({ status: 202 });
    } catch (err) {
        console.log(err);
        return next(err);
    }
});


module.exports = router;