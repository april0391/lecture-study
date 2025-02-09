const mongoose = require("mongoose");

const subscriberSchema = new mongoose.Schema(
  {
    // 채널 주인
    userTo: {
      type: mongoose.Schema.Types.ObjectId,
      ref: "User",
    },
    // 구독자
    userFrom: {
      type: mongoose.Schema.Types.ObjectId,
      ref: "User",
    },
  },
  { timestamps: true }
);

const Subscription = mongoose.model("Subscription", subscriberSchema);
module.exports = Subscription;
