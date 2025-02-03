const createHttpError = require("http-errors");
const jwtUtils = require("../utils/jwtUtils");

const verifyToken = (req, res, next) => {
  const headerValue = req.headers["authorization"];
  if (!headerValue) {
    throw createHttpError(400, "Authorization header is missing");
  }

  const token = headerValue.replace("Bearer ", "");
  try {
    const decoded = jwtUtils.verifyToken(token);
    req.decoded = decoded;
    next();
  } catch (error) {
    throw createHttpError(400, error);
  }
};

module.exports = { verifyToken };
