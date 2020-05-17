const mongoose = require("../mongoose");
const Schema = mongoose.Schema;

const userSchema = new Schema({
  nickname: {
    type: String,
    required: true,
    unique: true,
    minlength: 3,
  },
});

const uniqueValidator = require("mongoose-unique-validator");
userSchema.plugin(uniqueValidator);

const User = mongoose.model("User", userSchema);

User.processErrors = (err) => {
  let msg = {};
  for (let key in err.errors) {
    msg[key] = err.errors[key].message;
  }
  return msg;
};

module.exports = User;
