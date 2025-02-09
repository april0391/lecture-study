const router = require("express").Router();
const commentController = require("../controllers/commentController");

router.post("/saveComment", commentController.save);
router.post("/getComments", commentController.findByPostId);

module.exports = router;
