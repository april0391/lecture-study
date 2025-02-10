const mongoose = require("mongoose");
const User = require("../models/User");
const bcryptUtils = require("../utils/bcryptUtils");
const createHttpError = require("http-errors");

const findAll = async () => {
  return await User.find();
};

const save = async (data) => {
  const hashedPassword = await bcryptUtils.hash(data.password);
  const user = new User({ ...data, password: hashedPassword });
  return await user.save();
};

const findById = async (id) => {
  const user = await User.findById(id);
  checkUserExistsOrThrow(user);
  return user;
};

const updateById = async (id, data) => {
  const user = await User.findByIdAndUpdate(id, data, { new: true });
  checkUserExistsOrThrow(user);
  return user;
};

const deleteById = async (id) => {
  const user = await User.findByIdAndDelete(id);
  checkUserExistsOrThrow(user);
  return user;
};

const checkUserExistsOrThrow = (user) => {
  if (!user) {
    throw createHttpError(404, "User not found.");
  }
};

module.exports = { findAll, save, findById, updateById, deleteById };
