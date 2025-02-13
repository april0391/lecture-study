const bcrypt = require("bcryptjs");

const saltRounds = 10;

const hash = async (rawPassword) => {
  return await bcrypt.hash(rawPassword, saltRounds);
};

const validatePassword = async (inputPassword, dbPassword) => {
  return await bcrypt.compare(inputPassword, dbPassword);
};

module.exports = { hash, validatePassword };
