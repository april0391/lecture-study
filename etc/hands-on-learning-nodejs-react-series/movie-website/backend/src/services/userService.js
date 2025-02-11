const mongoose = require("mongoose");
const User = require("../models/User");
const bcrypt = require("../utils/bcryptUtils");

const findAll = async () => {
  return await User.find();
};

const findById = async (id) => {
  return await User.findById(id);
};

const save = async ({ email, password, username }) => {
  const encryptedPassword = await bcrypt.hash(password);
  const user = new User({ email, password: encryptedPassword, username });
  return await user.save();
};

const updateById = async (req, res) => {};

const deleteById = async (req, res) => {};

module.exports = { findAll, save, findById, updateById, deleteById };
