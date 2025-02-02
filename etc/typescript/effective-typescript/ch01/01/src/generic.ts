function fn<T>(value: T): T {
  return value;
}

let num = fn(10);
let bool = fn(true);
let str = fn("10");

let arr = fn<[number, number, number]>([1, 2, 3]);
