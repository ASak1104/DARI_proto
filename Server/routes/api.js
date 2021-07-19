const express = require('express');
const mapRouter = require('./api/map');

const router = express.Router();

router.use('/map', mapRouter);

module.exports = router;