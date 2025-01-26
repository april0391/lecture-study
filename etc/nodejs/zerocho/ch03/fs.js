const fs = require("fs");
const fsp = require("fs").promises;

fs.readFile("./readme.txt", (err, data) => {
  if (err) throw err;
  // console.log(data);
  // console.log(data.toString());
});

fsp
  .readFile("./readme.txt")
  .then((data) => console.log(data.toString()))
  .catch((err) => {
    throw err;
  });

const func = async () => {
  const data = await fsp.readFile("./readme.txt");
  console.log(data.toString());
};

func();
