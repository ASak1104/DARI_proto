const express = require('express');
const profileRouter = require('./user/profile');
const locationRouter = require('./user/location');
const imageRouter = require('./user/image');

const router = express.Router();

router.use('/profile', profileRouter);
router.use('/location', locationRouter);
router.use('/image', imageRouter);

module.exports = router;