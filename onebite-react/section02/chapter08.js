// 5가지 요소 순회 및 탐색 메서드

// 1. forEach
// 모든 요소를 순회하면서, 각각의 요소에 특정 동작을 수행
let arr1 = [1, 2, 3];
let doubledArr = [];
arr1.forEach((_, idx) => {
  doubledArr.push(idx);
});
// console.log(doubledArr);

// 2. includes
// 배열에 특정 요소가 있는지 확인
let arr2 = [1, 2, 3];
let isInclude = arr2.includes(4);
// console.log(isInclude);

// 3. indexOf
// 특정 요소의 인덱스를 찾아서 반환
let arr3 = [1, 2, 3];
let index = arr3.indexOf(2);

// 4. findIndex
// 모든 요소를 순회하면서 콜백함수를 만족하는 특정 요소의 인덱스를 반환
let arr4 = [1, 2, 3];
const findedIndex = arr4.findIndex((item) => item % 2 !== 0);

let objectArr1 = [{ name: "이정환" }, { name: "홍길동" }];

const result = objectArr1.findIndex((item) => item.name === "홍길동");
// console.log(result);

// 5. find
// 모든 요소를 순회하면서 콜백함수를 만족하는 요소를 찾아 반환

let objectArr2 = [{ name: "이정환" }, { name: "홍길동" }];
const fineded = objectArr2.find((item) => item.name === "이정환");
console.log(fineded);
