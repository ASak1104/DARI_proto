const express = require('express');
const jwt = require('jsonwebtoken');

const { verifyToken } = require('../middlewares');
const User = require('../../schemas/user');

const router = express.Router();

router.get('/test', verifyToken, (req, res) => {
    res.json(req.decoded);
});

module.exports = router;
