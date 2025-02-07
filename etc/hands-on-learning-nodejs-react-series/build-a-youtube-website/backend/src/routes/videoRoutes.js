const router = require("express").Router();
const videoController = require("../controllers/videoController");

router.post("/uploadFiles", videoController.uploadFiles);
router.post("/thumbnail", videoController.createThumbnail);
router.post("/uploadVideo", videoController.uploadVideo);

router.get("/getVideos", videoController.getVideos);
router.post("/getVideo", videoController.getVideoById);

module.exports = router;
