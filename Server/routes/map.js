const express = require('express');
const passport = require('passport');
const { isSignedIn, isNotSignedIn } = require('./middlewares');
const User = require('../schemas/user');
const Interest = require('../schemas/interest');
const UserToInterest = require('../schemas/userToInterest');

const router = express.Router();


/* GET /map/id */
// need to id -> _id
// need to make MW finding interest DB using _id
// apply isSignedIn MW later
router.get('/:id', async (req, res, next) => {
    try {
        const user = await User.findOne( { id: req.params.id }).lean();
        const userInterestIds = await UserToInterest.find({ user: user._id }, 'interest').lean();

        const getUserInterests = async () => {
            return await Promise.all(userInterestIds.map(async (item) => {
                const interest = await Interest.findById(item.interest, 'name -_id').lean()
                return interest.name;
            }))
        };

        const getOtherUsers = async () => {
            const objs2D = await Promise.all(userInterestIds.map(async (item) => {
                return UserToInterest.find({ _id: { $ne: item._id },  interest: item.interest }, 'user -_id').lean()
            }));
            const ids = await Promise.all(objs2D.map(async (items) => {
                return items.map((item) => item.user)
            })).then((arr2D) => [...new Set(arr2D.flat())]);
            return Promise.all(ids.map(async (id) => User.findById(id)));
        };

        const [userInterests, otherUsers] = await Promise.all([getUserInterests(), getOtherUsers()]);

        // res.json({
        //     latitude: user.latitude,
        //     longitude: user.longitude,
        //     interests: userInterests,
        //     otherUsers: otherUsers,
        // });
        res.json({
            "name": "박종찬",
            "id": "chan11",
            "latitude": 37.61149833100172,
            "longitude": 126.9972889,
            "introduce": "안녕하세요. 다리 CTO 박종찬입니다.",
            "interests": ["자전거", "음악감상", "독서", "게임", "축구"],
            "otherUsers": [
                {
                    "name": "강호산",
                    "id": "kang12",
                    "latitude": 37.609994611164375,
                    "longitude": 127.00435570418057,
                    "introduce": "안녕하세요. 다리 공동대표 강호산입니다.",
                    "interests": ["자전거", "게임", "산책", "축구"]
                },
                {
                    "name": "김현우",
                    "id": "ASak1104",
                    "latitude": 37.61086041699499,
                    "longitude": 127.00668782848335,
                    "introduce": "안녕하세요. 다리 서버개발팀장 김현우입니다.",
                    "interests": ["코딩", "게임", "수영"]
                },
                {
                    "name": "임종수",
                    "id": "lim123",
                    "latitude": 37.6069643116952,
                    "longitude": 126.9995310269911,
                    "introduce": "안녕하세요. 다리 앱개발팀장 임종수입니다.",
                    "interests": ["헬스", "음악감상", "산책", "독서"]
                },
                {
                    "name": "김민호",
                    "id": "kim123",
                    "latitude": 37.61036173272332,
                    "longitude": 126.97786860976362,
                    "introduce": "안녕하세요. 다리 공동대표 김민호입니다.",
                    "interests": ["자전거", "달리기", "독서"]
                }
            ]
        });
    } catch (err) {
        console.log(err);
        return next(err);
    }
});


module.exports = router;