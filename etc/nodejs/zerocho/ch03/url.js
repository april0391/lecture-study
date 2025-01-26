import dns from "dns/promises";
// const url = require("url");

const myUrl = new URL("https://google.com?a=b");

// console.log(myUrl);

const ip = await dns.resolve("google.com", "A");
console.log(ip);
