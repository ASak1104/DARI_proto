const express = require('express');
const path = require('path');

const router = express.Router();


/* GET home page. */
router.get('/', (req, res, next) => {
    res.sendFile(path.resolve('test.html'));
});

module.exports = router;
