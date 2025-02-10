import { useState } from "react";
import { Avatar, Button, Input } from "antd";
import { Comment } from "@ant-design/compatible";
import axios from "axios";
import LikeDislikes from "./LikeDislikes";
import { BACKEND_URL } from "../../config/constants";

const userId = localStorage.getItem("userId");

function SingleComment(props) {
  const [CommentValue, setCommentValue] = useState("");
  const [OpenReply, setOpenReply] = useState(false);

  const handleChange = (e) => {
    setCommentValue(e.currentTarget.value);
  };

  const openReply = () => {
    setOpenReply(!OpenReply);
  };

  const onSubmit = (e) => {
    e.preventDefault();

    const payload = {
      writer: userId,
      postId: props.postId,
      responseTo: props.comment._id,
      content: CommentValue,
    };

    axios
      .post(`${BACKEND_URL}/api/comment/saveComment`, payload)
      .then((res) => {
        if (res.data) {
          setCommentValue("");
          setOpenReply(!OpenReply);
          props.refreshFunction(res.data.content);
        } else {
          alert("Failed to save Comment");
        }
      });
  };

  const actions = [
    <LikeDislikes
      key={props.comment._id}
      commentId={props.comment._id}
      userId={userId}
    />,
    <span onClick={openReply} key="comment-basic-reply-to">
      Reply to{" "}
    </span>,
  ];

  return (
    <div>
      <Comment
        actions={actions}
        author={props.comment.writer}
        avatar={<Avatar src="" alt="image" />}
        content={<p>{props.comment.content}</p>}
      />

      {OpenReply && (
        <form style={{ display: "flex" }} onSubmit={onSubmit}>
          <Input.TextArea
            style={{ width: "100%", borderRadius: "5px" }}
            onChange={handleChange}
            value={CommentValue}
            placeholder="Write some comments"
          />
          <br />
          <Button style={{ width: "20%", height: "52px" }} onClick={onSubmit}>
            Submit
          </Button>
        </form>
      )}
    </div>
  );
}

export default SingleComment;
