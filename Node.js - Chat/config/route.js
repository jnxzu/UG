const express = require("express");
const router = new express.Router();

const passport = require("./passport");

const Room = require("./models/chatroom");

const rejectMethod = (_req, res, _next) => {
  res.sendStatus(405);
};

router
  .route("/")
  .get((req, res) => {
    if (!req.isAuthenticated()) res.redirect("/enter");
    else {
      Room.find({})
        .lean()
        .exec((err, docs) => {
          res.render("home", { nickname: req.user.nickname, rooms: docs });
        });
    }
  })
  .post((req, res) => {})
  .all(rejectMethod);

router
  .route("/room")
  .get((req, res) => {
    if (!req.isAuthenticated()) res.redirect("/enter");
    else
      Room.findOne({ name: req.query.name.substr(5) })
        .lean()
        .exec((err, docs) => {
          res.render("room", { nickname: req.user.nickname, room: docs });
        });
  })
  .all(rejectMethod);

router
  .route("/enter")
  .get((req, res) => {
    if (req.isAuthenticated()) res.redirect("/");
    res.render("enter");
  })
  .post(passport.authenticate("local-login"), async (req, res) => {
    await res.redirect("/");
  })
  .all(rejectMethod);

router
  .route("/logout")
  .get((req, res) => {
    if (!req.isAuthenticated()) res.redirect("/enter");
    else {
      req.logout();
      res.redirect("/");
    }
  })
  .all(rejectMethod);

module.exports = router;
