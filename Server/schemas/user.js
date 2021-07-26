const mongoose = require('mongoose');
const Interest = require('../schemas/interest');
const UserToInterest = require('../schemas/userToInterest');

const { Schema } = mongoose;
const { getDate } = require('../routes/middlewares');
const userSchema = new Schema({
    userId: {
        type: String,
        required: true,
        unique: true,
        index: true,
    },
    password: {
        type: String,
        required: true,
    },
    name: {
        type: String,
        required: true,
    },
    isOnline: {
        type: Boolean,
        default: false,
    },
    introduce: String,
    latitude: Number,
    longitude: Number,
    createdAt: {
        type: String,
        default: getDate,
    },
});

userSchema.statics.addInterests = async (user) => {
    const interestIds = await UserToInterest.find({ user: user._id }, 'interest -_id').lean();
    user.interests = await Promise.all(interestIds.map(async (item) => {
        return await Interest.findById(item.interest, 'name -_id').lean().then((obj) => obj.name);
    }));
};

// userSchema.virtual('image')
//     .set(function(url) {
//         this.image = url;
//     })
//     .get(function() {
//         return this.image;
//     });


module.exports = mongoose.model('User', userSchema);
