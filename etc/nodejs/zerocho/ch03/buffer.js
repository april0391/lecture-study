const buffer = Buffer.from("저를 버퍼로 바꿔보세요");
console.log(buffer);
console.log(buffer.length);
console.log(buffer.toString());

const array = [
  Buffer.from("띄엄 "),
  Buffer.from("띄엄 "),
  Buffer.from("띄어쓰기"),
];
console.log(Buffer.concat(array).toString());

const fs = require("fs");
const readStream = fs.createReadStream("./readme2.txt", { highWaterMark: 16 });

const data = [];

readStream.on("data", (chunk) => {
  data.push(chunk);
  console.log("data: ", chunk, chunk.length);
});
readStream.on("end", () => {
  console.log("end: ", Buffer.concat(data).toString());
});
