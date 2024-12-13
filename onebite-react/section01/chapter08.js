// 1. null 병합 연산자
let var1;
let var2 = 10;
let var3 = 20;

let var4 = var1 ?? var2;
let var5 = var1 ?? var3;
let var6 = var2 ?? var3;

console.log(var6);

// 2. typeof 연산자
let var7 = 1;

var7 = "hello";

console.log(typeof var7);

// 3. 삼항 연산자
let var8 = 10;
let res = var8 % 2 == 0 ? "짝수" : "홀수";
console.log(res);
