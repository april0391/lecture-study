// 1. if 조건문
let num = 10;

if (num >= 10) {
  console.log("num은 10 이상입니다");
} else {
  console.log("num은 10 미만입니다.");
}

// 2. switch문
let animal = "cat";
switch (animal) {
  case "cat": {
    console.log("고양이");
    break;
  }
  case "dog": {
    console.log("강아지");
    break;
  }
  case "bear": {
    console.log("곰");
    break;
  }
  case "goat": {
    console.log("염소");
    break;
  }
  case "tiger": {
    console.log("호랑이");
    break;
  }
  default: {
    console.log("default");
  }
}
