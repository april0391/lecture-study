// const { add, sub } = require("./math"); // commonjs 방식

// esm 방식
import multiply from "./math.js";
import { add, sub } from "./math.js";
import randomColor from "randomcolor";

const color = randomColor();
console.log(color);
