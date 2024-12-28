// 함수 선언
function greeting() {
  console.log("안녕하세요!");
}

let result = getArea(10, 20);
console.log(result);

// 호이스팅
function getArea(width, height) {
  let area = width * height;
  return area;
}
