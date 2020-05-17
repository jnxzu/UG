const Room = require("./models/chatroom");

function msg(io, socket, sys, room, content) {
  let msg = {
    author: socket.request.user.nickname,
    content: content,
    sys: sys,
  };
  Room.findOneAndUpdate({ name: room }, { $push: { messages: msg } }, () => {});
  io.emit("newmsg", {
    author: msg.author,
    content: msg.content,
    sys: sys,
  });
}

module.exports = function (io) {
  io.on("connection", (socket) => {
    var room = "";
    socket.on("joinroom", (data) => {
      room = data.room;
      msg(io, socket, true, data.room, "HAS JOINED THE ROOM");
    });
    socket.on("sendmsg", (data) => {
      msg(io, socket, false, data.room, data.content);
    });
    socket.on("disconnect", () => {
      msg(io, socket, true, room, "HAS LEFT THE ROOM");
    });
    socket.on("makeroom", (data) => {
      var newRoom = new Room();
      newRoom.name = data;
      newRoom.save();
      io.emit("add-room", newRoom.name);
    });
  });
};
