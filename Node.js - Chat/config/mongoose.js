const mongoose = require("mongoose");

mongoose.set("useFindAndModify", false);

(async () => {
  try {
    await mongoose.connect("mongodb://localhost/chatdb", {
      useUnifiedTopology: true,
      useNewUrlParser: true,
      useCreateIndex: true,
    });
  } catch (err) {
    console.error(err);
    process.exit(1);
  }
})();

const db = mongoose.connection;

db.on("open", () => {
  console.log("Database connected!\n");
});

db.on("error", console.error.bind(console, "MongoDb Error: "));

module.exports = mongoose;
