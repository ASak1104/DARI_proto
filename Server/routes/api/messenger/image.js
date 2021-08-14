const express = require('express');
const { getDate, verifyToken, imageDirs, upload } = require('../../middlewares');
const Channel = require('../../../schemas/channel');
const Message = require('../../../schemas/message');
const path = require('path');
const fs = require('fs');

const router = express.Router();


/* GET api/messenger/image/:file page */
router.get('/:file', verifyToken, async (req, res, next) => {
    try {
        const file = path.join(__dirname, `../../../${ imageDirs.messenger }/${ req.params.file }`);
        fs.access(file, (err) => {
            if (err) {
                console.error(err);
                res.status(404).send();
            } else {
                res.sendFile(path.join(__dirname, `../../../${ imageDirs.messenger }/${ req.params.file }`));
            }
        });
    } catch(err) {
        console.log(err);
        return next(err);
    }
});


/* POST api/messenger/image page */
router.post('/', verifyToken, upload(imageDirs.messenger, true).single('image'), async (req, res, next) => {
    try {
        const { channel_id } = req.body;
        const image = req.file.filename;
        const createdAt = req.decoded.imageCreatedAt;

        const [ , { users } ] = await Promise.all([
            Message.create({
                channel: channel_id,
                user: req.decoded._id,
                image,
                createdAt,
            }),
            Channel.findByIdAndUpdate(channel_id, {
                lastMessage: '사진을 보냈습니다.', updatedAt: createdAt,
            }).lean(),
        ]);

        const io = req.app.get('io');

        io.to(users.map(String)).emit('newMessage', {
            channel_id,
            userId: req.decoded.userId,
            userName: req.decoded.userName,
            content: '사진을 보냈습니다.',
            image,
            createdAt,
        });

        delete req.decoded.imageCreatedAt;
        res.status(201).json({ status: 201 });
    } catch(err) {
        console.log(err);
        return next(err);
    }
});


module.exports = router;