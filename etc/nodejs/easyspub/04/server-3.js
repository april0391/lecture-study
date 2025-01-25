const http = require("http");

const server = http.createServer((req, res) => {
  // req.url : 요청 경로
  // req.method : 요청 메서드
  const { url, method } = req;

  res.setHeader("Content-type", "text/plain");
  if (method === "GET" && url === "/home") {
    res.write("HOME");
    res.end();
  } else if (method === "GET" && url === "/about") {
    res.end("ABOUT");
  } else {
    res.end("Not Found");
  }
});

server.listen(8080, () => {
  console.log("서버 실행 중");
});
