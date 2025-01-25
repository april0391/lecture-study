const http = require("http");

const server = http.createServer((req, res) => {
  // console.log(req);
  res.setHeader("Content-type", "text/plain");
  res.write("Hello Node");
  res.end();
});

server.listen(8080, () => {
  console.log("서버 실행 중");
});
