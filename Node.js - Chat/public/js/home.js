document
  .getElementById("add-room")
  .getElementsByTagName("h1")[0]
  .addEventListener("click", () => {
    socket.emit("makeroom", document.getElementById("new-room-name").value);
  });

socket.on("add-room", (data) => {
  var rooms = document.getElementById("grid-container");
  var div = document.createElement("div");
  div.className = "chatroom-box";
  var a = document.createElement("a");
  a.href = "/room?name=room-" + data;
  var h = document.createElement("h1");
  h.innerText = data;
  a.appendChild(h);
  div.appendChild(a);
  rooms.prepend(div);
});
