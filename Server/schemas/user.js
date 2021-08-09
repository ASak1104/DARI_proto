const mongoose = require('mongoose');
const Interest = require('../schemas/interest');
const UserToInterest = require('../schemas/userToInterest');

const { Schema } = mongoose;
const { Types: { ObjectId } } = Schema;
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
    userName: {
        type: String,
        required: true,
    },
    introduce: String,
    location: {
        type: {
            type: String, // Don't do `{ location: { type: String } }`
            enum: ['Point'], // 'location.type' must be 'Point'
        },
        coordinates: {
            type: [Number],
        }
    },
    interests: [
        {
            type: ObjectId,
            ref: 'Interest',
        }
    ],
    createdAt: {
        type: String,
        default: getDate,
    },
});

userSchema.index({ location: "2dsphere" });


module.exports = mongoose.model('User', userSchema);
