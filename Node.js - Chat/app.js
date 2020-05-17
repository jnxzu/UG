const express = require("express");
const app = express();

const path = require("path");

const sio = require("socket.io");
const pSio = require("passport.socketio");

const session = require("express-session");
const MongoStore = require("connect-mongo")(session);
const mongoose = require("./config/mongoose");
const sessionStore = new MongoStore({
  mongooseConnection: mongoose.connection,
});

const passport = require("./config/passport");

const cookieParser = require("cookie-parser");
const bodyParser = require("body-parser");
app.use(cookieParser());
app.use(bodyParser.urlencoded({ extended: true }));

const sessionSecret = "neverkn0w";
const sessionKey = "app.sid";
app.use(
  session({
    key: sessionKey,
    secret: sessionSecret,
    store: sessionStore,
    resave: false,
    saveUninitialized: true,
  })
);

app.use(passport.initialize());
app.use(passport.session());

app.set("view engine", "ejs");
app.use("/public", express.static(path.join(__dirname, "public")));

const routes = require("./config/route.js");

app.use(routes);
app.use((_, res) => {
  res.sendStatus(404);
});

const server = require("http").createServer(app);

const io = sio.listen(server);

io.use(
  pSio.authorize({
    cookieParser: cookieParser,
    key: sessionKey,
    secret: sessionSecret,
    store: sessionStore,
    success: (data, accept) => {
      accept(null, true);
    },
    fail: (data, message, error, accept) => {
      accept(null, false);
    },
  })
);

require("./config/io.js")(io);

server.listen(3000, () => {
  console.log(`http://localhost:3000`);
});
