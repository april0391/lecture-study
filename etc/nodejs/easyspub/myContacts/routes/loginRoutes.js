const express = require("express");
const router = express.Router();
const {
  getLogin,
  login,
  getRegister,
  registerUser,
} = require("../controllers/loginController");

router //
  .route("/")
  .get(getLogin)
  .post(login);

router //
  .route("/register")
  .get(getRegister)
  .post(registerUser);

module.exports = router;
