const mongoose = require("mongoose");
const Comment = require("../models/Comment");

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

module.exports = { save, findByPostId };
