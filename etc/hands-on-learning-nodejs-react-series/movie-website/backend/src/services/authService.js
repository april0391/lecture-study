require("dotenv").config();
const bcryptUtils = require("../utils/bcryptUtils");
const jwt = require("jsonwebtoken");
const User = require("../models/User");
const createHttpError = require("http-errors");

const JWT_SECRET = process.env.JWT_SECRET;

const authenticate = async (email, password) => {
  const findUser = await User.findOne({ email });
  if (!findUser) {
    throw createHttpError(401, "Not authenticated.");
  }
  const isValidPassword = await bcryptUtils.validatePassword(
    password,
    findUser.password
  );
  if (!isValidPassword) {
    throw createHttpError(401, "Not authenticated.");
  }
  return findUser;
};

const createToken = (payload, expiresInSecond) => {
  return jwt.sign(payload, JWT_SECRET, {
    expiresIn: expiresInSecond,
  });
};

const verifyToken = (token) => {
  return jwt.verify(token, JWT_SECRET);
};

module.exports = { authenticate, createToken, verifyToken };
