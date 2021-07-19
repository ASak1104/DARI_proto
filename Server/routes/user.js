const express = require('express');
const profileRouter = require('./profile');
const locationRouter = require('./location');

const router = express.Router();

router.use(profileRouter);
router.use(locationRouter);

module.exports = router;