const jwt = require('jsonwebtoken');
const moment = require('moment');
require('moment-timezone');

moment.tz.setDefault('Asia/Seoul');


exports.getDate = () => {
    return moment().format('YYYY-MM-DD HH:mm:ss:ms');
};


exports.verifyToken = (req, res, next) => {
    try {
        req.decoded = jwt.verify(req.headers.authorization, process.env.JWT_SECRET);
        return next();
    } catch (error) {
        if (error.name === 'TokenExpiredError') {
            return res.status(419).json({
                status: 419,
                message: 'The token has expired',
            });
        }
        return res.status(401).json({
            status: 401,
            message: 'Invalid token',
        });
    }
};
