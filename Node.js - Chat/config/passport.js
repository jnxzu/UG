const passport = require("passport");
const LocalStrategy = require("passport-local").Strategy;

const User = require("./models/user");

passport.use(
  "local-login",
  new LocalStrategy(
    { usernameField: "nickname", passwordField: "nickname" },
    function (nickname, password, done) {
      User.findOne({ nickname: nickname }, function (err, user) {
        if (err) return done(err);
        else if (!user) {
          console.log(
            `User with nickname ${nickname} not found, creating new user.`
          );
          var newUser = new User();
          newUser.nickname = nickname;
          newUser.save(function (err) {
            if (err) throw err;
            else {
              console.log(`Connecting as ${nickname}.`);
              return done(null, newUser);
            }
          });
        } else {
          console.log(`Connecting as ${nickname}.`);
          return done(null, user);
        }
      });
    }
  )
);

passport.serializeUser((user, done) => {
  done(null, user);
});

passport.deserializeUser((obj, done) => {
  done(null, obj);
});

module.exports = passport;
