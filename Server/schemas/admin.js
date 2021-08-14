const mongoose = require('mongoose');

const adminSchema = mongoose.Schema({
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
    role : {
        type: String,
        enum: ['admin', 'restricted'],
        required: true,
    },
});

const Admin = mongoose.model('Admin', adminSchema);

module.exports = { Admin }