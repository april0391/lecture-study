require("dotenv").config();
const createHttpError = require("http-errors");
const bcryptUtils = require("../utils/bcryptUtils");
const jwtUtils = require("../utils/jwtUtils");
const User = require("../models/User");

const loginProcess = async (data) => {
  const user = await validateUserCredentials(data);
  const token = jwtUtils.signToken({ _id: user._id });
  return { token, userId: user._id };
};

const validateUserCredentials = async (data) => {
  const { email, password } = data;
  const user = await User.findOne({ email });
  if (!user) {
    throw createHttpError(404, "Email not found.");
  }

  const isValid = await bcryptUtils.validatePassword(password, user.password);
  if (!isValid) {
    throw createHttpError(401, "Password not valid.");
  }
  return user;
};

module.exports = { loginProcess };
