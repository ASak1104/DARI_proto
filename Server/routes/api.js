const express = require('express');
const authRouter = require('./api/auth');
const mapRouter = require('./api/map');
const messengerRouter = require('./api/messenger');
const tokenRouter = require('./api/v1.0.0');

const router = express.Router();

router.use('/auth', authRouter);
router.use('/map', mapRouter);
router.use('/messenger', messengerRouter);
router.use('/token', tokenRouter);

module.exports = router;