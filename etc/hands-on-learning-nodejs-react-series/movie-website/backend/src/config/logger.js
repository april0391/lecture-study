const winston = require("winston");
const morgan = require("morgan");

const logger = winston.createLogger({
  level: "info", // 최소 레벨 (이 레벨 이상의 로그를 출력)
  format: winston.format.combine(
    winston.format.colorize(), // 로그 메시지를 색상화
    winston.format.timestamp({ format: "YYYY-MM-DD HH:mm:ss" }),
    winston.format.printf(({ timestamp, level, message }) => {
      return `${timestamp} [${level}]: ${message}`;
    }) // 출력 형식 지정
  ),
  transports: [
    // 콘솔에 로그 출력
    new winston.transports.Console(),
    // 파일에 로그 저장
    // new winston.transports.File({ filename: "application.log" }),
  ],
});

const morganMiddleware = morgan(
  ':remote-addr ":method :url HTTP/:http-version" :status :res[content-length] ":referrer" ":user-agent"',
  {
    stream: { write: (message) => logger.info(message.trim()) },
  }
);

// 예시 로그
// logger.info("This is an info message.");
// logger.error("This is an error message.");

module.exports = { logger, morganMiddleware };
