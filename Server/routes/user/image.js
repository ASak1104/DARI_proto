const express = require('express');
const path = require('path');
const fs = require('fs');

const { verifyToken, imageDirs,  upload } = require('../middlewares');

for (const key in imageDirs) {
    try {
        fs.readdirSync(imageDirs[key]);
    } catch (error) {
        console.error(`Make ${ imageDirs[key] } repository`);
        fs.mkdirSync(imageDirs[key]);
    }
}

const router = express.Router();


/* GET user/image/:id page */
router.get('/:id', verifyToken, (req, res) => {
    const file = path.join(__dirname, `../../${ imageDirs.user }/${ req.params.id }.jpg`);
    fs.access(file, (err) => {
        if (err) {
            // console.error(err);
            res.status(404).send();
        } else {
            res.sendFile(path.join(__dirname, `../../${ imageDirs.user }/${ req.params.id }.jpg`));
        }
    });
});


/* POST user/image page */
router.post('/', verifyToken, upload(imageDirs.user).single('image'), (req, res) => {
    res.json({ url: `${ process.env.DOMAIN }/${ imageDirs.user }/${ req.decoded.userId }/image` });
});


/* PUT user/image page */
router.put('/', verifyToken, upload(imageDirs.user).single('image'), (req, res) => {
    res.json({ url: `${ process.env.DOMAIN }/${ imageDirs.user }/${ req.decoded.userId }/image` });
});


module.exports = router;
