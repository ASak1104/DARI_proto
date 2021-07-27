const express = require('express');
const multer = require('multer');
const path = require('path');
const fs = require('fs');

const { getDate, verifyToken } = require('../middlewares');
const imageRepo = 'uploads'

const router = express.Router();

try {
    fs.readdirSync(imageRepo);
} catch (error) {
    console.error('Make uploads repository');
    fs.mkdirSync(imageRepo);
}

const upload = multer({
    storage: multer.diskStorage({
        destination(req, file, cb) {
            cb(null, imageRepo);
        },
        filename(req, file, cb) {
            const ext = path.extname(file.originalname);
            if(!['.png', '.jpg', '.jpeg', '.gif'].includes(ext)) {
                return cb(new Error('Only images are allowed'));
            }
            cb(null, req.decoded.userId + '.jpg');
        },
    }),
    limits: { fileSize: 5 * 1024 * 1024 },
});


/* GET user/image/:id page */
router.get('/:id', verifyToken, (req, res) => {
    const file = path.join(__dirname, `../../${ imageRepo }/${ req.params.id }.jpg`);
    fs.access(file, (err) => {
        if (err) {
            // console.error(err);
            res.status(404).send();
        } else {
            res.sendFile(path.join(__dirname, `../../${ imageRepo }/${ req.params.id }.jpg`));
        }
    });
});


/* POST user/image page */
router.post('/', verifyToken, upload.single('image'), (req, res) => {
    res.json({ url: `user/${req.decoded.userId}/image` });
});


/* PUT user/image page */
router.put('/', verifyToken, upload.single('image'), (req, res) => {
    res.json({ url: `user/${req.decoded.userId}/image` });
});


module.exports = router;
