const express = require('express');
const multer = require('multer');
const path = require('path');
const fs = require('fs');

const { getDate } = require('../middlewares');

const router = express.Router();

try {
    fs.readdirSync('uploads');
} catch (error) {
    console.error('Make uploads repository');
    fs.mkdirSync('uploads');
}

const upload = multer({
    storage: multer.diskStorage({
        destination(req, file, cb) {
            cb(null, 'uploads/');
        },
        filename(req, file, cb) {
            const ext = path.extname(file.originalname);
            cb(null, req.params.id + ext);
        },
    }),
    limits: { fileSize: 5 * 1024 * 1024 },
});


/* GET user/:image page */
// need to change url
router.get('/:image', (req, res) => {
    const ext = path.extname(path.join(__dirname, `../../uploads/${req.params.image}`));
    res.sendFile(path.join(__dirname, `../../uploads/${req.params.image}`));
});


/* POST user/image page */
router.post('/image', upload.single('image'), (req, res) => {
    console.log(req.file);
    res.json({ url: `user/${req.file.filename}` });
});


/* PUT user/image page */
router.put('/image', upload.single('image'), (req, res) => {
    console.log(req.file);
    res.json({ url: `user/${req.file.filename}` });
});


module.exports = router;