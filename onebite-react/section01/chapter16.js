// 1. 상수 객체
const animal = {
  type: "고양이",
  name: "나비",
  color: "black",
};

animal.age = 2; // 추가
animal.name = "고양이"; // 수정
delete animal.color; // 삭제

console.log(animal);

// 2. 메서드
const person = {
  name: "이정환",
  // 메서드
  sayHi() {
    console.log("안녕하세요");
  },
};

person.sayHi();
person["sayHi"]();
