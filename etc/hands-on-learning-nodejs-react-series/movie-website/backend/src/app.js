const express = require("express");
const app = express();
require("dotenv").config();
require("express-async-errors");
const cors = require("cors");
const { logger, morganMiddleware } = require("./config/logger");
const PORT = process.env.NODE_PORT;
const mongodb = require("./config/mongodb");

const userRoutes = require("./routes/userRoutes");

// Configuration
mongodb.connect();

// app.use(morgan("dev"));
app.use(morganMiddleware);

// Middlewares
app.use(
  cors({
    origin: "http://localhost:5173",
    credentials: true, // 쿠키 허용
  })
);
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

app.use("/users", userRoutes);

// Global Error Handling
app.use((err, req, res, next) => {
  logger.error(err);
  const statusCode = err.status || 500;
  const message = err.message || "Internal Server Error";
  const body = { message };
  if (err.errors) {
    body.errors = err.errors;
  }
  res.status(statusCode).json(body);
});

app.listen(PORT, () => {
  logger.info(`서버 실행중. 포트: ${PORT}`);
});
