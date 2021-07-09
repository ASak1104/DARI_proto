const express = require('express');
const User = require('../schemas/user');

const router = express.Router();

/* GET users listing. */
router.get('/', (req, res, next) => {
    res.send('<h1>Test /users GET router</h1>');
});

router.post('/', async (req, res, next) => {
    console.log(req.body);
    try {
        const existUser = await User.findOne({ id: req.body.id });
        console.log(existUser);
        if (!existUser) {
            const newUser = await User.create({
                id: req.body.id,
                password: req.body.password,
                name: req.body.name,
            })
            console.log('회원가입 성공');
        } else {
            console.log('동일한 아이디가 존재합니다.');
        }
        res.json({ register: !existUser });
    } catch (err) {
        console.log(err);
        next(err);
    }
});

module.exports = router;
