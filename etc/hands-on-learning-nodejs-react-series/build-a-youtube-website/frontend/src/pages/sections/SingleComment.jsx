import React, { useState } from "react";
import { Card, Avatar, Button, Input, Tooltip } from "antd";
import Axios from "axios";
import LikeDislikes from "./LikeDislikes";
const { TextArea } = Input;

function SingleComment(props) {
  const [CommentValue, setCommentValue] = useState("");
  const [OpenReply, setOpenReply] = useState(false);
  const [Likes, setLikes] = useState(0); // 예시로 좋아요 상태도 추가

  const handleChange = (e) => {
    setCommentValue(e.currentTarget.value);
  };

  const openReply = () => {
    setOpenReply(!OpenReply);
  };

  const onSubmit = (e) => {
    e.preventDefault();

    const variables = {
      writer: "userId", // 실제 유저 데이터로 교체
      postId: props.postId,
      responseTo: props.comment._id,
      content: CommentValue,
    };

    Axios.post("/api/comment/saveComment", variables).then((response) => {
      if (response.data.success) {
        setCommentValue("");
        setOpenReply(!OpenReply);
        props.refreshFunction(response.data.result);
      } else {
        alert("Failed to save Comment");
      }
    });
  };

  return (
    <div>
      <Card
        actions={[
          <LikeDislikes commentId={props.comment._id} />,
          <span onClick={openReply}>Reply to </span>,
        ]}
        title={props.comment.writer.name}
        extra={<Avatar src={props.comment.writer.image} alt="image" />}
      >
        <p>{props.comment.content}</p>
        {OpenReply && (
          <form style={{ display: "flex" }} onSubmit={onSubmit}>
            <TextArea
              style={{ width: "100%", borderRadius: "5px" }}
              onChange={handleChange}
              value={CommentValue}
              placeholder="write some comments"
            />
            <Button style={{ width: "20%" }} onClick={onSubmit}>
              Submit
            </Button>
          </form>
        )}
      </Card>
    </div>
  );
}

export default SingleComment;
