const moment = require('moment');
require('moment-timezone');

moment.tz.setDefault('Asia/Seoul');

exports.getDate = () => {
    return moment().format('YYYY-MM-DD HH:mm:ss:ms');
};

exports.isSignedIn = (req, res, next) => {
    if (req.isAuthenticated()) {
        next();
    } else {
        res.status(403).send('Need to Login');
    }
};

exports.isNotSignedIn = (req, res, next) => {
    if(!req.isAuthenticated()) {
        next();
    } else {
        const message = encodeURIComponent('Already Login');
        res.redirect(`/?error=${message}`);
    }
};