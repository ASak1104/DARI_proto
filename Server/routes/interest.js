const express = require('express');
const passport = require('passport');
const { isSignedIn, isNotSignedIn } = require('./middlewares');
const User = require('../schemas/user');
const Interest = require('../schemas/interest');
const UserToInterest = require('../schemas/userToInterest');

const router = express.Router();


/* POST /interest */
router.post('/', isSignedIn, async (req, res, next) => {

    res.json({})
});


module.exports = router;