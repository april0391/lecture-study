const mongoose = require("mongoose");
const { logger } = require("./logger");

const connect = async () => {
  try {
    await mongoose.connect(
      "mongodb://root:0000@localhost:27017/movie_website?authSource=admin"
    );
    logger.info("MongoDB Connected!");
  } catch (error) {
    logger.error("MongoDB connection error: ", error);
  }
};

module.exports = { connect };
