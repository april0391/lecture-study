import React, { useState } from "react";
import axios from "axios";
import SingleComment from "./SingleComment";
import ReplyComment from "./ReplyComment";

function Comments(props) {
  const user = {};
  const [Comment, setComment] = useState("");

  const handleChange = (e) => {
    setComment(e.currentTarget.value);
  };

  const onSubmit = (e) => {
    e.preventDefault();

    if (user.userData && !user.userData.isAuth) {
      return alert("Please Log in first");
    }

    const variables = {
      content: Comment,
      writer: user.userData._id,
      postId: props.postId,
    };
    console.log(variables);

    axios.post("/api/comment/saveComment", variables).then((response) => {
      if (response.data.success) {
        setComment("");
        props.refreshFunction(response.data.result);
      } else {
        alert("Failed to save Comment");
      }
    });
  };

  return (
    <div className="w-full">
      <br />
      <h3 className="text-xl font-semibold">
        {" "}
        Share your opinions about {props.movieTitle}{" "}
      </h3>
      <hr className="my-4" />
      {/* Comment Lists */}
      {console.log(props.CommentLists)}

      {props.CommentLists &&
        props.CommentLists.map(
          (comment, index) =>
            !comment.responseTo && (
              <React.Fragment key={index}>
                <SingleComment
                  comment={comment}
                  postId={props.postId}
                  refreshFunction={props.refreshFunction}
                />
                <ReplyComment
                  CommentLists={props.CommentLists}
                  postId={props.postId}
                  parentCommentId={comment._id}
                  refreshFunction={props.refreshFunction}
                />
              </React.Fragment>
            )
        )}

      {props.CommentLists && props.CommentLists.length === 0 && (
        <div className="flex justify-center items-center h-48 text-gray-600">
          Be the first one who shares your thought about this movie
        </div>
      )}

      {/* Root Comment Form */}
      <form className="flex flex-col space-y-4" onSubmit={onSubmit}>
        <textarea
          className="w-full p-4 border border-gray-300 rounded-lg resize-none"
          onChange={handleChange}
          value={Comment}
          placeholder="Write some comments"
        />
        <button
          className="bg-blue-500 text-white py-2 px-6 rounded-md hover:bg-blue-600"
          onClick={onSubmit}
        >
          Submit
        </button>
      </form>
    </div>
  );
}

export default Comments;
