const express = require("express");
const expressLayouts = require("express-ejs-layouts");
require("dotenv").config();
const connectDb = require("./config/db");
const cookieParser = require("cookie-parser");

connectDb();

const app = express();
const port = process.env.PORT || 4000;

app.use(expressLayouts);
app.set("view engine", "ejs");
app.set("views", "./views");

app.use(express.static("public"));

app.use(express.json());
app.use(express.urlencoded({ extended: true }));

app.use(cookieParser);

app.use("/", require("./routes/main"));
app.use("/", require("./routes/admin"));

app.listen(port, () => {
  console.log(`App listening on port ${port}`);
});
