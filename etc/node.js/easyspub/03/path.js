const path = require("path");

const fullPath = path.join("some", "work", "ex.txt");

const dir = path.dirname(fullPath);

const fn1 = path.basename(__filename);
const fn2 = path.basename(__filename, ".js"); // 확장자 제거

console.log(fn2);
