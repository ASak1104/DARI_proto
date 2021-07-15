const mongoose = require('mongoose');

const { Schema } = mongoose;
const { Types: { ObjectId } } = Schema;
const interestSchema = new Schema({
    name: {
        type: String,
        required: true,
        unique: true,
    },
    userCount: {
        type: Number,
        default: 0,
    },
    createdAt: {
        type: Date,
        default: Date.now() + (60 * 60 * 1000 * 9),
    }
});

module.exports = mongoose.model('Interest', interestSchema);
