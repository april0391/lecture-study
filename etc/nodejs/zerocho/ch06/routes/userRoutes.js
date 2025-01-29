const express = require("express");
const router = express.Router();

router.get("/", (req, res) => {
  console.log("유저 라우터");
  res.send("유저 라우터");
});

router.get("/:id", (req, res) => {
  res.send(req.params);
});

module.exports = router;
