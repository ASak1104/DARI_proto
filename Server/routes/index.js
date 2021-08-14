const express = require('express');
const path = require('path');

const router = express.Router();


/* GET home page. */
router.get('/', (req, res, next) => {
    res.status(403).send();
});

module.exports = router;
