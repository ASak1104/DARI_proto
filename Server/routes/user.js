const express = require('express');
const profileRouter = require('./user/profile');
const locationRouter = require('./user/location');
const imageRouter = require('./user/image');

const router = express.Router();

router.use(profileRouter);
router.use(locationRouter);
router.use(imageRouter);

module.exports = router;