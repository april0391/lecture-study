const loginService = require("../services/loginService");

const loginProcess = async (req, res) => {
  const { token, userId } = await loginService.loginProcess(req.body);
  res.json({ token, userId });
};

module.exports = { loginProcess };
