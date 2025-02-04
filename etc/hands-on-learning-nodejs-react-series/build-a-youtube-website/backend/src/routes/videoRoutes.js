const router = require("express").Router();
const multer = require("multer");
const path = require("path");

const Video = require("../models/videoModel"); // 영상 모델 임포트

const storage = multer.diskStorage({
  destination: (req, file, cb) => {
    cb(null, "uploads/");
  },
  filename: (req, file, cb) => {
    const fileName = path.basename(
      file.originalname,
      path.extname(file.originalname)
    ); // 파일명만 추출
    const ext = path.extname(file.originalname); // 파일 확장자 추출
    cb(null, `${fileName}_${Date.now()}${ext}`); // 파일명_현재시간_확장자
  },
});

const fileFilter = (req, file, cb) => {
  const allowedTypes = /mp4|avi|mov|mkv/;
  const extname = allowedTypes.test(
    path.extname(file.originalname).toLowerCase()
  );
  const mimetype = allowedTypes.test(file.mimetype);

  if (extname && mimetype) {
    return cb(null, true);
  }
  cb("Error: Only video files are allowed!");
};

const upload = multer({
  storage: storage,
  fileFilter: fileFilter,
  limits: { fileSize: 50 * 1024 * 1024 },
});

const uploadFile = upload.single("file");

router.post("/uploadfiles", (req, res) => {
  uploadFile(req, res, (err) => {
    if (err) {
      // Multer 오류가 있을 경우 처리
      console.error("Multer error:", err);
      let errorMessage = "An error occurred during file upload.";

      // Multer 오류의 종류에 따라 다른 메시지 처리
      if (err.code === "LIMIT_FILE_SIZE") {
        errorMessage = "File size exceeds the limit (50MB).";
      } else if (err.message) {
        errorMessage = err.message; // Multer의 기본 메시지 사용
      }

      return res.status(400).send(`Error: ${errorMessage}`);
    }

    if (!req.file) {
      // 파일이 없을 경우 처리
      console.error("No file uploaded");
      return res.status(400).send("No file uploaded.");
    }

    // 업로드 성공 시
    console.log("File uploaded successfully:", req.file);

    // 성공 응답
    return res.json({
      message: "File uploaded successfully!",
      file: req.file,
    });
  });
});

module.exports = router;
