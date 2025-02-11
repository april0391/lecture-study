const bcrypt = require("bcryptjs");

const saltRounds = 10;

const hash = async (rawPassword) => {
  return await bcrypt.hash(rawPassword, saltRounds);
};

const validatePassword = async (rawPassword) => {
  const hashedPassword = await hash(rawPassword);
  return await bcrypt.compare(rawPassword, hashedPassword);
};

module.exports = { hash, validatePassword };
