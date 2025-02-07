require("dotenv").config();
const createHttpError = require("http-errors");
const jwt = require("jsonwebtoken");

const JWT_SECRET = process.env.JWT_SECRET;

const signToken = (payload) => {
  return jwt.sign(payload, JWT_SECRET, {
    expiresIn: "30m",
  });
};

const verifyToken = (token) => {
  try {
    return jwt.verify(token, JWT_SECRET);
  } catch (error) {
    console.error(error);
    throw createHttpError(400, error);
  }
};

module.exports = { signToken, verifyToken };
