const router = require("express").Router();
const loginController = require("../controllers/loginController");

router.post("/", loginController.loginProcess);

module.exports = router;
