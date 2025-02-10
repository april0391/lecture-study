const mongoose = require("mongoose");

const videoLikeSchema = new mongoose.Schema(
  {
    videoId: {
      type: mongoose.Schema.Types.ObjectId,
      ref: "Video",
      required: true,
    },
    userId: {
      type: mongoose.Schema.Types.ObjectId,
      ref: "User",
      required: true,
    },
    action: {
      type: String,
      enum: ["like", "dislike"],
      required: true,
    },
  },
  { timestamps: true }
);

videoLikeSchema.index({ videoId: 1, userId: 1 }, { unique: true });

const VideoLike = mongoose.model("VideoLike", videoLikeSchema);

module.exports = VideoLike;
