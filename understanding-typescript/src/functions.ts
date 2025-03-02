function add(n1: number, n2: number) {
  return n1 + n2;
}

function printResult(num: number): undefined {
  console.log("Result: " + num);
}

function addAndHandle(n1: number, n2: number, cb: (num: number) => void) {
  const result = n1 + n2;
  cb(result);
}

addAndHandle(10, 20, () => {
  console.log();
});

let combineValues: (n1: number, n2: number) => number;

combineValues = add;
// combineValues = printResult;

// console.log(combineValues(8, 8));

const func = (): string => {
  return "hello";
};
