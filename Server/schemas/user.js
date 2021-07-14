const mongoose = require('mongoose');

const { Schema } = mongoose;
const { Types: { ObjectId } } = Schema;
const userSchema = new Schema({
    id: {
        type: String,
        required: true,
        unique: true,
    },
    password: {
        type: String,
        required: true,
    },
    name: {
        type: String,
        required: true,
    },
    comment: String,
    latitude: Number,
    longitude: Number,
    interests: [
        {
            type: ObjectId,
            ref: 'Interest',
        }
    ]
});

module.exports = mongoose.model('User', userSchema);
