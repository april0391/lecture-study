const router = require("express").Router();
const userController = require("../controllers/userController");
const userValidator = require("../middlewares/userValidator");
const objectIdValidator = require("../middlewares/objectIdValidator");

router //
  .route("/")
  .get(userController.findAll)
  .post(userValidator.validateFormValue, userController.save);

router
  .route("/:id")
  .all(objectIdValidator.validate)
  .get(userController.findById)
  .patch(userValidator.validateFormValue, userController.updateById)
  .delete(userController.deleteById);

module.exports = router;
