const express = require("express");
const router = express.Router();

const userController = require("../controllers/userController");
const paramsValidator = require("../middlewares/paramsValidator");

router //
  .route("/")
  .get(userController.findAll)
  .post(userController.save);

router
  .use("/:id", paramsValidator.validateObjectId)
  .route("/:id")
  .get(userController.findById)
  .patch(userController.updateById)
  .delete(userController.deleteById);

module.exports = router;
