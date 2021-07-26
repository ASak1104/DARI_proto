const express = require('express');
const jwt = require('jsonwebtoken');

const { verifyToken } = require('../middlewares');
const User = require('../../schemas/user');

const router = express.Router();

router.post('/', async (req, res) => {
    const { userId } = req.body;
    try {
        const user = await User.findOne( { userId }, 'userId, name');
        if (!user) {
            return res.status(401).json({
                code: 401,
                message: 'Non-existent user',
            });
        }
        const token = jwt.sign({
            _id: user._id,
            userId,
            name: user.name,
        }, process.env.JWT_SECRET, {
            expiresIn: '10m', // 1 minute
            issuer: 'DARI',
        });
        return res.json({
            code: 200,
            message: 'A token has been issued',
            token,
        });
    } catch (error) {
        console.error(error);
        return res.status(500).json({
            code: 500,
            message: 'Server error',
        });
    }
});

router.get('/test', verifyToken, (req, res) => {
    res.json(req.decoded);
});

module.exports = router;
