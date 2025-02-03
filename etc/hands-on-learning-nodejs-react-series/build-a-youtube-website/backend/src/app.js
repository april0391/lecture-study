const express = require("express");
const app = express();
const port = 4000;
const morgan = require("morgan");
const cors = require("cors");
const mongodb = require("./config/mongodb");
require("express-async-errors");

const userRoutes = require("./routes/userRoutes");
const loginRoutes = require("./routes/loginRoutes");

const authMiddleware = require("./middlewares/authMiddleware");

mongodb.connect();

// Middlewares
app.use(
  cors({
    origin: "http://localhost:5173",
    credentials: true, // 쿠키 허용
  })
);
app.use(express.json()); // 요청 본문 JSON 파싱
app.use(express.urlencoded({ extended: true })); // HTML 폼 데이터를 req.body로 변환
app.use(morgan("dev"));

// Routing
app.use("/users", userRoutes);
app.use("/login", loginRoutes);

// temp
app.use("/temp", authMiddleware.verifyToken, (req, res) => {
  res.send("ok");
});

// Global Error Handling
app.use((err, req, res, next) => {
  const statusCode = err.status || 500;
  const message = err.message || "Internal Server Error";
  res.status(statusCode).json({ message });
});

app.listen(port, () => {
  console.log("서버 실행중");
});
