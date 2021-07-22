const express = require('express');
const mapRouter = require('./api/map');
const messengerRouter = require('./api/messenger');

const router = express.Router();

router.use('/map', mapRouter);
router.use('/messenger', messengerRouter);

module.exports = router;