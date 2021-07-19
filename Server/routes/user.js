const express = require('express');
const profileRouter = require('./user/profile');
const locationRouter = require('./user/location');

const router = express.Router();

router.use(profileRouter);
router.use(locationRouter);

module.exports = router;