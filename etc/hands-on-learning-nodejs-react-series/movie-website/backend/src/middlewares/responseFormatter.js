const responseFormatter = (req, res, next) => {
  // 원래 res.json 함수 백업
  const originalJson = res.json;

  // res.json 오버라이딩
  res.json = (data) => {
    const formattedData = {
      success: true,
      message: "Success", // 기본 메시지 (수정 가능)
      data: data, // 컨트롤러에서 보내는 실제 데이터
    };

    originalJson.call(res, formattedData); // 기존 json 메서드 호출
  };

  next();
};

module.exports = responseFormatter;
