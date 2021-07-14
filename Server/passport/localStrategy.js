const passport = require('passport');
const LocalStrategy = require('passport-local').Strategy;
const bcrypt = require('bcrypt');

const User = require('../schemas/user');

module.exports = () => {
    passport.use(new LocalStrategy({
        usernameField: 'id',
        passwordField: 'password',
    }, async (id, password, done) => {
        try {
            const exUser = await User.findOne({ id });
            if (exUser) {
                const result = await bcrypt.compare(password, exUser.password);
                if (result) {
                    done(null, exUser, { status: 200 });
                } else {
                    done(null, false, { status: 400 });
                }
            } else {
                done(null, false, { status: 300 });
            }
        } catch (err) {
            console.log(err);
            done(err);
        }
    }));
};