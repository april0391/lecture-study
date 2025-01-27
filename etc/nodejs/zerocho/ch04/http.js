const http = require("http");
const fs = require("fs");

const index = fs.readFileSync("./index.html");

http
  .createServer({}, (req, res) => {
    res.setHeader("Content-type", "text/html");
    res.end(index);
  })
  .listen(8080, () => {
    console.log("8080번 포트에서 서버 실행");
  });
