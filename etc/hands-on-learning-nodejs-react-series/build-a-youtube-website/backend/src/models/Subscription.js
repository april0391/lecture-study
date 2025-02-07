const mongoose = require("mongoose");

const subscriberSchema = new mongoose.Schema(
  {
    userTo: {
      type: mongoose.Schema.Types.ObjectId,
      ref: "User",
    },
    userFrom: {
      type: mongoose.Schema.Types.ObjectId,
      ref: "User",
    },
  },
  { timestamps: true }
);

const Subscription = mongoose.model("Subscription", subscriberSchema);
module.exports = Subscription;
