import React, { useState, useEffect } from "react";
import { Button, Input } from "antd";
import axios from "axios";
import SingleComment from "./SingleComment";
import ReplyComment from "./ReplyComment";
import { BACKEND_URL } from "../../config/constants";
const { TextArea } = Input;

function Comments({ postId, commentLists, refreshFunction }) {
  const [comment, setComment] = useState("");
  const [userId, setUserId] = useState();

  useEffect(() => {
    setUserId(localStorage.getItem("userId"));
  }, []);

  const handleChange = (e) => {
    setComment(e.currentTarget.value);
  };

  const onSubmit = (e) => {
    e.preventDefault();

    if (!userId) {
      alert("로그인이 필요합니다.");
      return;
    }

    const payload = {
      content: comment,
      writer: userId,
      postId,
    };

    axios
      .post(`${BACKEND_URL}/api/comment/saveComment`, payload)
      .then((res) => {
        if (res.data) {
          setComment("");
          refreshFunction(res.data.content);
        } else {
          alert("Failed to save Comment");
        }
      });
  };

  return (
    <div>
      <br />
      <p>replies</p>
      <hr />
      {/* Comment Lists */}
      {commentLists &&
        commentLists.map(
          (comment, index) =>
            !comment.responseTo && (
              <React.Fragment key={comment._id}>
                <SingleComment
                  comment={comment}
                  postId={postId}
                  refreshFunction={refreshFunction}
                />
                <ReplyComment
                  commentLists={commentLists}
                  postId={postId}
                  parentCommentId={comment._id}
                  refreshFunction={refreshFunction}
                />
              </React.Fragment>
            )
        )}

      {/* Root Comment Form */}
      <form style={{ display: "flex" }} onSubmit={onSubmit}>
        <TextArea
          style={{ width: "100%", borderRadius: "5px" }}
          onChange={handleChange}
          value={comment}
          placeholder="write some comments"
        />
        <br />
        <Button
          type="primary"
          style={{ width: "20%", height: "52px" }}
          onClick={onSubmit}
        >
          Submit
        </Button>
      </form>
    </div>
  );
}

export default Comments;
