const mongoose = require('mongoose');

const { Schema } = mongoose;
const { Types: { ObjectId } } = Schema;
const interestSchema = new Schema({
    name: {
        type: String,
        required: true,
        unique: true,
    },
    users: [
        {
            type: ObjectId,
            ref: 'User',
        }
    ],
    userCount: {
        type: Number,
        default: 0,
    },
    createdAt: {
        type: Date,
        default: Date.now,
    }
});

module.exports = mongoose.model('Interest', interestSchema);
