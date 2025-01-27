const path = require("path");
const express = require("express");
const app = express();

const morgan = require("morgan");

app.set("port", process.env.SERVER_PORT || 4000);

app.use(morgan("dev"));

app.use(
  (req, res, next) => {
    console.log("공통 기능 처리1");
    next();
  },
  (req, res, next) => {
    console.log("공통 기능 처리2");
    next();
  }
);

app.use("/abc", (req, res, next) => {
  console.log("abc 미들웨어");
  next();
});

app.get("/", (req, res) => {
  res.sendFile(path.join(__dirname, "./index.html"));
});

app.get("/about", (req, res) => {
  res.send("about");
});

app.get("/users/:id", (req, res) => {
  res.send(`user ${req.params.id} 정보 응답`);
});

app.get("/posts/:id", (req, res) => {
  const id = req.params.id;
  res.json({ id });
});

app.get("/error", (req, res) => {
  throw new Error();
});

app.get("*", (req, res) => {
  res.send("*");
});

app.use((err, req, res, next) => {
  console.error(err);
  res.status(500).send("에러가 발생했습니다");
});

app.listen(app.get("port"), () => {
  console.log(`port ${app.get("port")} 에서 서버 실행`);
});
