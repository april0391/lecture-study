import React, { useEffect, useState } from "react";
import SingleComment from "./SingleComment";

const ReplyComment = ({
  commentLists,
  parentCommentId,
  postId,
  refreshFunction,
}) => {
  const [childCommentCount, setChildCommentCount] = useState(0);
  const [openReplyComments, setOpenReplyComments] = useState(false);

  useEffect(() => {
    const commentNumber = commentLists.filter(
      (comment) => comment.responseTo === parentCommentId
    ).length;
    setChildCommentCount(commentNumber);
  }, [commentLists, parentCommentId]);

  const handleChange = () => {
    setOpenReplyComments((prev) => !prev);
  };

  const renderReplyComments = (parentCommentId) => {
    return commentLists
      .filter((comment) => comment.responseTo === parentCommentId)
      .map((comment) => (
        <div key={comment._id} style={{ width: "80%", marginLeft: "40px" }}>
          <SingleComment
            comment={comment}
            postId={postId}
            refreshFunction={refreshFunction}
          />
          <ReplyComment
            commentLists={commentLists}
            parentCommentId={comment._id}
            postId={postId}
            refreshFunction={refreshFunction}
          />
        </div>
      ));
  };

  return (
    <div>
      {childCommentCount > 0 && (
        <p
          style={{ fontSize: "14px", margin: 0, color: "gray" }}
          onClick={handleChange}
        >
          View {childCommentCount} more comment(s)
        </p>
      )}

      {openReplyComments && renderReplyComments(parentCommentId)}
    </div>
  );
};

export default ReplyComment;
