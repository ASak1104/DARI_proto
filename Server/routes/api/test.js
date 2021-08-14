const express = require('express');
const jwt = require('jsonwebtoken');

const { verifyToken } = require('../middlewares');
const User = require('../../schemas/user');
const Interest = require('../../schemas/interest');
const UserToInterest = require('../../schemas/userToInterest');

const router = express.Router();


router.get('/token', verifyToken, (req, res) => {
    res.json(req.decoded);
});


router.patch('/fix-userCount', async (req, res) => {
    const interests = await Interest.find();
    await Promise.all(interests.map(async (interest) => {
        const userCount = await UserToInterest.find({ interest: interest._id }, '_id').count();
        await interest.update({ userCount });
    }));
    res.json({ status: 200 });
});


module.exports = router;
