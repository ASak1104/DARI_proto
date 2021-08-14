const jwt = require('jsonwebtoken');
const moment = require('moment');
require('moment-timezone');
const multer = require('multer');
const path = require('path');
const fs = require('fs');

moment.tz.setDefault('Asia/Seoul');


const getDate = () => {
    return moment().format('YYYY-MM-DD HH:mm:ss:ms');
};

exports.getDate = getDate


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


const baseDir = 'uploads'
exports.imageDirs = {
    base: baseDir,
    user: `${ baseDir }/user`,
    supporter: `${ baseDir }/supporter`,
    messenger: `${ baseDir }/messenger`,
}


exports.upload = (dir, nameWithDate=false) => multer({
    storage: multer.diskStorage({
        destination(req, file, cb) {
            cb(null, dir);
        },
        filename(req, file, cb) {
            const ext = path.extname(file.originalname);
            if(!['.png', '.jpg', '.jpeg', '.gif'].includes(ext)) {
                return cb(new Error('Only images are allowed'));
            }
            if (nameWithDate) {
                req.decoded.imageCreatedAt = getDate();
                cb(null, `${ req.decoded.userId }_${ req.decoded.imageCreatedAt.replace(' ', '_') }${ ext }`);
            } else {
                cb(null, `${ req.decoded.userId }.jpg`)
            }
        },
    }),
    limits: { fileSize: 5 * 1024 * 1024 },
});