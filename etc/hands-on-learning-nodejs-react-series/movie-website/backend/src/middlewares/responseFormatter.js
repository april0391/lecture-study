const responseFormatter = (req, res, next) => {
  // 원래 res.json 함수 백업
  const originalJsonFn = res.json;

  // res.json 오버라이딩
  res.json = (data) => {
    const message = data.message || "Success";
    delete data.message;
    const responseData = {
      message: message,
      data: data,
    };
    originalJsonFn.call(res, responseData); // 기존 json() 호출
  };

  // 예외 발생시 응답할 함수
  res.errorJson = (err) => {
    const message = err.message || "Internal Server Error";
    const responseData = {
      success: false,
      message: message,
    };
    if (err.errors) responseData.data = err.errors;
    res.status(err.status || 500);
    originalJsonFn.call(res, responseData);
  };

  next();
};

module.exports = responseFormatter;
