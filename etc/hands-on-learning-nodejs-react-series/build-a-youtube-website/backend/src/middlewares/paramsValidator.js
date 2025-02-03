const mongoose = require("mongoose");
const createHttpError = require("http-errors");

const validateObjectId = (req, res, next) => {
  if (!mongoose.Types.ObjectId.isValid(req.params.id)) {
    throw createHttpError(400, "Invalid user ID format");
  }
  next();
};

module.exports = { validateObjectId };
