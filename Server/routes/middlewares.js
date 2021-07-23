const User = require('../schemas/user');
const Interest = require('../schemas/interest');
const UserToInterest = require('../schemas/userToInterest');
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

exports.userWithInterests = async (user) => {
    const interestIds = await UserToInterest.find({ user: user._id }, 'interest -_id').lean();
    user.interests = await Promise.all(interestIds.map(async (item) => {
        return Interest.findById(item.interest, 'name -_id').lean().then((obj) => obj.name);
    }));
    delete user._id;
};