const mongoose = require('mongoose');

const { Schema } = mongoose;
const { Types: { ObjectId } } = Schema;
const userToInterestSchema = new Schema({
    user:{
        type: ObjectId,
        required: true,
        ref: 'User',
    },
    interest: {
        type: ObjectId,
        required: true,
        ref: 'Interest',
    },
});

module.exports = mongoose.model('UserToInterest', userToInterestSchema);