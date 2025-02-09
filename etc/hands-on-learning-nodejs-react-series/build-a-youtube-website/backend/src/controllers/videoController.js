const multer = require("multer");
const path = require("path");
const ffmpeg = require("fluent-ffmpeg");
const jwtUtils = require("../utils/jwtUtils");

const Video = require("../models/Video");
const Subscription = require("../models/Subscription");

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

const uploadFiles = (req, res) => {
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
      filePath: req.file.path,
      fileName: req.file.filename,
    });
  });
};

const createThumbnail = (req, res) => {
  let filePath = "";
  let fileDuration = "";
  // 비디오 정보 가져오기
  ffmpeg.ffprobe(req.body.filePath, (err, metadata) => {
    console.dir(metadata);
    console.log(metadata.format.duration);
    fileDuration = metadata.format.duration;
  });

  // 썸네일 생성
  ffmpeg(req.body.filePath)
    .on("filenames", function (filenames) {
      console.log("Will generate " + filenames.join(", "));
      console.log(filenames);

      filePath = "uploads/thumbnails/" + filenames[0];
    })
    .on("end", function () {
      console.log("Screenshots taken");
      return res.json({
        success: true,
        url: filePath,
        fileDuration: fileDuration,
      });
    })
    .on("error", function (err) {
      console.error(err);
      return res.json({ success: false, err });
    })
    .screenshots({
      count: 3,
      folder: "uploads/thumbnails",
      size: "320x240",
      filename: "thumbnail-%b.png",
    });
};

const uploadVideo = (req, res) => {
  const { writer: token } = req.body;
  const payload = jwtUtils.verifyToken(token);

  const video = new Video({ ...req.body, writer: payload._id });
  video.save();
  res.status(201).json(video);
};

const getVideos = async (req, res) => {
  const videos = await Video.find().populate("writer");
  res.json(videos);
};

const getVideoById = async (req, res) => {
  const { videoId } = req.body;
  const findVideo = await Video.findById(videoId).populate("writer");
  console.log(findVideo);

  res.json(findVideo);
};

const getSubscriptionVideos = async (req, res) => {
  const { userFrom } = req.body;
  const subscriptions = await Subscription.find({ userFrom });
  console.log(subscriptions);

  // subscriptions 배열에서 userTo 값만 추출해서 새로운 배열로 만든다.
  const userTos = subscriptions.map((subscription) => subscription.userTo);
  console.log(userTos);

  // userToList에 해당하는 비디오를 찾는다.
  const videos = await Video.find({ writer: { $in: userTos } }).populate(
    "writer"
  );
  res.json(videos);
};

module.exports = {
  uploadFiles,
  createThumbnail,
  uploadVideo,
  getVideos,
  getVideoById,
  getSubscriptionVideos,
};
