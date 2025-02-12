const { body, validationResult } = require("express-validator");
const createHttpError = require("http-errors");

const validateFormValue = [
  body("email")
    .isEmail()
    .withMessage("유효한 이메일 형식을 입력하세요.")
    .normalizeEmail(),
  body("password")
    .isLength({ min: 6 })
    .withMessage("비밀번호는 최소 6자 이상이어야 합니다.")
    .matches(/^(?=.*[A-Za-z])(?=.*\d)/)
    .withMessage("비밀번호는 최소 하나의 문자와 숫자를 포함해야 합니다."),
  body("username")
    .isLength({ min: 2 })
    .withMessage("사용자 이름은 최소 2자 이상이어야 합니다.")
    .trim(),
  validationHandler,
];

function validationHandler(req, res, next) {
  const errors = validationResult(req);
  if (!errors.isEmpty()) {
    throw createHttpError(400, "유효성 검사 실패", { errors: errors.array() });
  }
  next();
}

module.exports = { validateFormValue };
