const express = require('express');
const channelRouter = require('./messenger/channel');
const messageRouter = require('./messenger/message');
const imageRouter = require('./messenger/image');

const router = express.Router();

router.use('/channel', channelRouter);
router.use('/message', messageRouter);
router.use('/image', imageRouter);


module.exports = router;