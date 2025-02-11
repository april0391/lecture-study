const router = require("express").Router();
const userController = require("../controller/userController");

router //
  .route("/")
  .get(userController.findAll)
  .post(userController.save);

router //
  .route("/:id")
  .get(userController.findById)
  .put(userController.update)
  .post(userController.delete);

module.exports = router;
