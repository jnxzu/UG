document.getElementById("send").addEventListener("click", () => {
  let msg = document.getElementById("new-msg").value;
  if (msg) {
    var room = window.location.search.substr(11);
    socket.emit("sendmsg", {
      content: msg,
      room: room,
    });
  }
});

window.onload = () => {
  var room = window.location.search.substr(11);
  room = room.replace("%20", " ");
  room = room.replace("%27", "'");
  socket.emit("joinroom", {
    room: room,
  });
};

socket.on("newmsg", (data) => {
  var chat = document.getElementById("chat");
  var div = document.createElement("div");
  div.className = "msg";
  if (data.sys)
    div.innerHTML = "<span>" + data.author + " </span>" + data.content;
  else div.innerHTML = "<span>" + data.author + ": </span>" + data.content;
  chat.append(div);
});
