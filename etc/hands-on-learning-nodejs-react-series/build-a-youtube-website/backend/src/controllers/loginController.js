const loginService = require("../services/loginService");

const loginProcess = async (req, res) => {
  const jwt = await loginService.loginProcess(req.body);
  res.json({ token: jwt });
};

module.exports = { loginProcess };
