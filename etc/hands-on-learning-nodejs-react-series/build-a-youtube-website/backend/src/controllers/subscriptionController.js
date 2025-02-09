const mongoose = require("mongoose");
const Subscription = require("../models/Subscription");

const getSubscriberNumber = async (req, res) => {
  const { userTo, userFrom } = req.body;
  const subscriberNumber = await Subscription.countDocuments({ userTo });
  res.json({ subscriberNumber });
};

const isSubscribed = async (req, res) => {
  const { userTo, userFrom } = req.body;
  const isSubscribed = await Subscription.findOne({ userTo, userFrom });
  console.log(!!isSubscribed);
  res.json({ isSubscribed: !!isSubscribed });
};

const subscribe = async (req, res) => {
  const { userTo, userFrom } = req.body;
  const model = new Subscription({ userTo, userFrom });
  const subscription = await model.save();
  return res.status(201).json(subscription);
};

const unSubscribe = async (req, res) => {
  const { userTo, userFrom } = req.body;
  const subscription = await Subscription.findOneAndDelete({
    userTo,
    userFrom,
  });
  return res.json(subscription);
};

module.exports = { getSubscriberNumber, isSubscribed, subscribe, unSubscribe };
