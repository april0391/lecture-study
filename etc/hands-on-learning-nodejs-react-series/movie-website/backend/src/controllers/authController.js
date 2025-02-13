const authService = require("../services/authService");

const loginProcess = async (req, res) => {
  const { email, password } = req.body;
  const user = await authService.authenticate(email, password);

  const expiresIn = 60 * 60;
  const accessToken = authService.createToken(
    { id: user._id, email: user.email },
    expiresIn
  );

  res.cookie("access_token", accessToken, {
    httpOnly: true,
    sameSite: "Strict",
  });

  res.json({
    user: {
      id: user._id,
      email: user.email,
    },
    token: {
      expiresAt: Math.floor(Date.now() / 1000) + expiresIn,
    },
  });
};

module.exports = { loginProcess };
