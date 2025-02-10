const mongoose = require("mongoose");

const connect = async () => {
  try {
    await mongoose.connect(
      "mongodb://root:0000@localhost:27017/youtube?authSource=admin"
    );
    console.log("MongoDB Connected!");
  } catch (error) {
    console.error("MongoDB connection error: ", error);
  }
};

module.exports = { connect };
