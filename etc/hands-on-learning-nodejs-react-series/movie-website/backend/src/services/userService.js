const mongoose = require("mongoose");
const User = require("../models/User");
const bcrypt = require("../utils/bcryptUtils");
const createHttpError = require("http-errors");

const findAll = async () => {
  return await User.find();
};

const save = async ({ email, password, username }) => {
  const encryptedPassword = await bcrypt.hash(password);
  const user = new User({ email, password: encryptedPassword, username });
  return await user.save();
};

const findById = async (id) => {
  const user = await User.findById(id);
  return checkUserExistsOrThrow(user);
};

const updateById = async (id, data) => {
  const user = await User.findByIdAndUpdate(id, data, { new: true });
  return checkUserExistsOrThrow(user);
};

const deleteById = async (id) => {
  const user = await User.findByIdAndDelete(id);
  return checkUserExistsOrThrow(user);
};

const checkUserExistsOrThrow = (user) => {
  if (!user) {
    throw createHttpError(404, "User not found.");
  }
  return user;
};

module.exports = { findAll, save, findById, updateById, deleteById };
