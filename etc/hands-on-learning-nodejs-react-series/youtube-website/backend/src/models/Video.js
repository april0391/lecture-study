const mongoose = require("mongoose");

const videoSchema = new mongoose.Schema(
  {
    writer: {
      type: mongoose.Schema.Types.ObjectId,
      ref: "User",
      required: true,
    },
    title: {
      type: String,
      maxLength: 50,
      required: true,
    },
    description: {
      type: String,
    },
    privacy: {
      type: Number,
      required: true,
    },
    filePath: {
      type: String,
      required: true,
    },
    category: {
      type: String,
    },
    views: {
      type: Number,
      default: 0,
    },
    duration: {
      type: String,
    },
    thumbnail: {
      type: String,
    },
  },
  { timestamps: true }
);

const Video = mongoose.model("Video", videoSchema);

module.exports = Video;
