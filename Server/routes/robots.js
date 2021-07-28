const express = require('express');
const path = require('path');

const router = express.Router();


/* GET robots.txt page. */
router.all('/', (req, res, next) => {
    res.render('robots.txt');
});

module.exports = router;
