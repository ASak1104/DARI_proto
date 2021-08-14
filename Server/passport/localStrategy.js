const passport = require('passport');
const LocalStrategy = require('passport-local').Strategy;
const bcrypt = require('bcrypt');

const User = require('../schemas/user');

module.exports = () => {
    passport.use(new LocalStrategy({
        usernameField: 'userId',
        passwordField: 'password',
    }, async (userId, password, done) => {
        try {
            const exUser = await User.findOne({ userId });
            if (exUser) {
                const result = await bcrypt.compare(password, exUser.password);
                if (result) {
                    done(null, exUser, { status: 201, message: 'Success to sign-in' });
                } else {
                    done(null, false, { status: 400, message: 'Wrong password' });
                }
            } else {
                done(null, false, { status: 300, message: 'User does not exist' });
            }
        } catch (err) {
            console.log(err);
            done(err);
        }
    }));
};