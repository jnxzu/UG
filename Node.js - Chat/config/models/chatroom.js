const mongoose = require("../mongoose");
const Schema = mongoose.Schema;

const message = new Schema({
  author: {
    type: String,
    required: true,
    minlength: 3,
  },
  content: {
    type: String,
    required: true,
  },
  sys: {
    type: Boolean,
    required: true,
  },
});

const roomSchema = new Schema({
  name: { type: String, required: true, minlength: 1 },
  messages: [message],
});

const Room = mongoose.model("Room", roomSchema);

Room.processErrors = (err) => {
  let msg = {};
  for (let key in err.errors) {
    msg[key] = err.errors[key].message;
  }
  return msg;
};

module.exports = Room;
