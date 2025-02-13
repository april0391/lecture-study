const router = require("express").Router();
const authController = require("../controllers/authController");

router.post("/login", authController.loginProcess);
router.post("/logout", authController.loginProcess);

module.exports = router;
