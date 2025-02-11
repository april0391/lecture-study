const express = require("express");
const app = express();
const port = 4000;
const morgan = require("morgan");
const cors = require("cors");
require("express-async-errors");
const mongodb = require("./config/mongodb");

const userRoutes = require("./routes/userRoutes");

mongodb.connect();

// Middlewares
app.use(
  cors({
    origin: "http://localhost:5173",
    credentials: true, // 쿠키 허용
  })
);
app.use(express.json());
app.use(express.urlencoded({ extended: true }));
app.use(morgan("dev"));

app.use("/users", userRoutes);

// Global Error Handling
app.use((err, req, res, next) => {
  console.error(err);
  const statusCode = err.status || 500;
  const message = err.message || "Internal Server Error";
  res.status(statusCode).json({ message });
});

app.listen(port, () => {
  console.log("서버 실행중");
});
