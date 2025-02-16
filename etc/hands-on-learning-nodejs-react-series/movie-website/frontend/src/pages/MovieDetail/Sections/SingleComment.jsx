import { useState } from "react";
import Axios from "axios";
import LikeDislikes from "./LikeDislikes";

function SingleComment(props) {
  const user = {};
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

    const variables = {
      writer: user.userData._id,
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

  const actions = [
    <LikeDislikes
      comment
      commentId={props.comment._id}
      userId={localStorage.getItem("userId")}
    />,
    <span
      onClick={openReply}
      key="comment-basic-reply-to"
      className="text-blue-500 cursor-pointer"
    >
      Reply to
    </span>,
  ];

  return (
    <div className="border-b p-4">
      <div className="flex items-center space-x-4">
        <img
          src={props.comment.writer.image}
          alt="avatar"
          className="w-10 h-10 rounded-full object-cover"
        />
        <div className="flex-1">
          <div className="flex justify-between items-center">
            <span className="font-semibold">{props.comment.writer.name}</span>
            <div>{actions}</div>
          </div>
          <p className="mt-2">{props.comment.content}</p>
        </div>
      </div>

      {OpenReply && (
        <form className="mt-4 flex" onSubmit={onSubmit}>
          <textarea
            className="w-full p-2 border border-gray-300 rounded-md"
            onChange={handleChange}
            value={CommentValue}
            placeholder="Write a reply..."
          ></textarea>
          <button
            type="submit"
            className="ml-4 bg-blue-500 text-white px-4 py-2 rounded-md"
          >
            Submit
          </button>
        </form>
      )}
    </div>
  );
}

export default SingleComment;
