const express = require('express');
const User = require('../schemas/user');

const router = express.Router();

/* GET users listing. */
router.get('/', (req, res, next) => {
    console.log(req.baseUrl);
    console.log(req.body);
    res.json({ testGet: true});
});

router.post('/', async (req, res, next) => {
    console.log(req.baseUrl);
    console.log(req.body);
    res.json({ register: true })
    // try {
    //     const existUser = await User.find({ id: req.body.id })
    //     console.log(existUser)
    //     if (!existUser) {
    //         const newUser = await User.create({
    //             id: req.body.id,
    //             password: req.body.password,
    //             name: req.body.name,
    //         })
    //         console.log(newUser);
    //     }
    //     res.json({ register: !existUser });
    // } catch (err) {
    //     console.log(err);
    //     next(err);
    // }
});

module.exports = router;
