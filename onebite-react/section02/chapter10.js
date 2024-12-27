// 1. Date 객체를 생성하는 방법
let date1 = new Date();

let date2 = new Date("1999-01-01/12:00:01");
// console.log(date2);

// 2. 타임 스탬프
let ts1 = date1.getTime();
// console.log(ts1);

let date3 = new Date(ts1);
// console.log(date3);

// 3. 시간 요소들을 추출하는 방법법
let year = date1.getFullYear();
let month = date1.getMonth() + 1;
let date = date1.getDate();

let hour = date1.getHours();
let minute = date1.getMinutes();
let second = date1.getSeconds();

// 4. 시간 수정하기
date1.setFullYear(2023);
date1.setMonth(2);
date1.setDate(30);

// 5. 시간을 여러 포맷으로 출력하기
console.log(date1.toDateString());
console.log(date1.toLocaleString());
