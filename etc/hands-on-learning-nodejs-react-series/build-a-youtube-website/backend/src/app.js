const express = require("express");
const app = express();
const port = 4000;
const path = require("path");
const morgan = require("morgan");
const cors = require("cors");
const mongodb = require("./config/mongodb");
require("express-async-errors");

const userRoutes = require("./routes/userRoutes");
const loginRoutes = require("./routes/loginRoutes");
const videoRoutes = require("./routes/videoRoutes");

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

// 정적 파일 서빙
const dir = path.join(__dirname, "../uploads");
console.log(dir);

app.use("/uploads", express.static(dir));

// Routing
app.use("/users", userRoutes);
app.use("/login", loginRoutes);
app.use("/api/video", videoRoutes);

app.post("/api/comment/getComments", (req, res) => {
  res.json({});
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
