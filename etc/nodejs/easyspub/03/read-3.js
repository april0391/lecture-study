const fs = require("fs");

fs.readFile("./example.txt", "utf8", (err, data) => {
  if (err) {
    console.log(err);
  }
  // console.log(data);
  fs.writeFile("./test.txt", data, (err) => {
    console.log(err);
  });
});
