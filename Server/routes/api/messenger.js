const express = require('express');
const { verifyToken } = require('../middlewares');
const User = require('../../schemas/user');
const Channel = require('../../schemas/channel');
const Message = require('../../schemas/message');
const UserToChannel = require('../../schemas/userToChannel');

const router = express.Router();


/* POST api/messenger/channel page */
// apply isSignedIn MW later
router.post('/channel', async (req, res, next) => {
    try {

    } catch (err) {
        console.log(err);
        return next(err);
    }
});


module.exports = router;