const express = require('express');
const authRouter = require('./api/auth');
const mapRouter = require('./api/map');
const messengerRouter = require('./api/messenger');
const testRouter = require('./api/test');

const router = express.Router();

router.use('/auth', authRouter);
router.use('/map', mapRouter);
router.use('/messenger', messengerRouter);
router.use('/test', testRouter);

module.exports = router;