const express = require('express');
const mapRouter = require('./map');

const router = express.Router();

router.use('/map', mapRouter);

module.exports = router;