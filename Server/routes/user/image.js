const express = require('express');
const multer = require('multer');
const path = require('path');
const fs = require('fs');

const { getDate } = require('../middlewares');
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
            cb(null, req.params.id + '.jpg');
        },
    }),
    limits: { fileSize: 5 * 1024 * 1024 },
});


/* GET user/:id/image page */
router.get('/:id/image', (req, res) => {
    const ext = path.extname(path.join(__dirname, `../../${imageRepo}/${req.params.image}`));
    res.sendFile(path.join(__dirname, `../../${imageRepo}/${req.params.id}.jpg`));
});


/* POST user/:id/image page */
router.post('/:id/image', upload.single('image'), (req, res) => {
    // console.log(req.file);
    res.json({ url: `user/${req.params.id}/image` });
});


/* PUT user/:id/image page */
router.put('/:id/image', upload.single('image'), (req, res) => {
    // console.log(req.file);
    res.json({ url: `user/${req.params.id}/image` });
});


module.exports = router;
