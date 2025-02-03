const bcrypt = require("bcryptjs");

const saltRounds = 10;

const hash = async (rawPassword) => {
  return await bcrypt.hash(rawPassword, saltRounds);
};

const validatePassword = async (rawPassword, hashedPassword) => {
  return await bcrypt.compare(rawPassword, hashedPassword);
};

module.exports = { hash, validatePassword };
