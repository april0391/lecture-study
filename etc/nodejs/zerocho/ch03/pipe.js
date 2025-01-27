const fs = require("fs");
const zlib = require("zlib");

const readStream = fs.createReadStream("./readme2.txt", { highWaterMark: 16 });
const zlibStream = zlib.createGzip();
const writeStream = fs.createWriteStream("./writeme.txt.gz");
readStream.pipe(zlibStream).pipe(writeStream);
