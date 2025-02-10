const mongoose = require("mongoose");
const Comment = require("../models/Comment");
const CommentLike = require("../models/CommentLike");

const save = async (req, res) => {
  const model = new Comment(req.body);
  const comment = await model.save();
  const pupulatedComment = await Comment.findOne(comment._id).populate(
    "writer"
  );
  res.status(201).json(pupulatedComment);
};

const findByPostId = async (req, res) => {
  const comments = await Comment.find({ postId: req.body.videoId }).sort({
    createdAt: -1,
  });
  res.json(comments);
};

const getLikeCount = async (req, res) => {
  const { commentId, userId } = req.query;
  const commentLikes = await CommentLike.find({ commentId });

  const { likeCount, dislikeCount, userAction } = commentLikes.reduce(
    (acc, commentLike) => {
      if (commentLike.action === "like") acc.likeCount++;
      if (commentLike.action === "dislike") acc.dislikeCount++;
      if (String(commentLike.userId) === String(userId))
        acc.userAction = commentLike.action;
      return acc;
    },
    { likeCount: 0, dislikeCount: 0, userAction: "none" }
  );

  res.json({ likeCount, dislikeCount, userAction });
};

const handleLikeAction = async (req, res) => {
  const { commentId, userId, action } = req.body;
  const existingLike = await CommentLike.findOne({ commentId, userId });

  // 같은 액션이면 삭제
  if (existingLike && existingLike.action === action) {
    await CommentLike.deleteOne({ commentId, userId });
    return res.json({ message: "Action removed" });
  }

  // 기존 반응이 없거나, 다른 반응이면 업데이트 (없으면 생성)
  await CommentLike.findOneAndUpdate(
    { commentId, userId },
    { action },
    { new: true, upsert: true }
  );

  res.json({ message: "Action updated" });
};

module.exports = { save, findByPostId, getLikeCount, handleLikeAction };
