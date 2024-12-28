// async
// 함수가 프로미스를 반환하도록 변환해주는 키워드
async function getData() {
  return {
    name: "이정환",
    id: "winterlood",
  };
}

// await: async 함수 내부에서만 사용이 가능한 키워드
async function printData() {
  const data = await getData();
  console.log(data);
}

printData();
