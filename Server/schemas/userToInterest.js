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
userToInterestSchema.index({ user: 1, interest: 1 }, { unique: true });

module.exports = mongoose.model('UserToInterest', userToInterestSchema);