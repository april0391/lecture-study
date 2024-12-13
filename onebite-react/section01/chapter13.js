// 1. 콜백 함수
function main(value) {
  //   console.log(value);
  value();
}

function sub() {
  console.log("i am sub");
}

// main(() => console.log("i am sub"));

// 2. 콜백 함수의 활용
function repeat(count, callback) {
  for (let idx = 1; idx <= count; idx++) {
    callback(idx);
  }
}

repeat(5, (idx) => console.log(idx * 2));
