const mongoose = require("mongoose");
const createHttpError = require("http-errors");

const validate = (req, res, next) => {
  const httpMethod = req.method;
  const objectId = req.params.id;
  if (!objectId || !mongoose.Types.ObjectId.isValid(objectId)) {
    throw createHttpError(400, "Invalid user ID format");
  }
  next();
};

module.exports = { validate };
