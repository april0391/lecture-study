const path = require("path");

const fullPath = path.join(__dirname, "path.js");
console.log(fullPath);

const parsed = path.parse(fullPath);
console.log(parsed);
