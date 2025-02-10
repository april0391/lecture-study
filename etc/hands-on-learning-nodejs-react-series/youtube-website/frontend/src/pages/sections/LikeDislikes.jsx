import { useEffect, useState } from "react";
import { Tooltip } from "antd";
import { LikeOutlined, DislikeOutlined } from "@ant-design/icons";
import axios from "axios";
import { BACKEND_URL } from "../../config/constants";

function LikeDislikes(props) {
  const [likeCount, setLikeCount] = useState(0);
  const [dislikeCount, setDislikeCount] = useState(0);
  const [likeAction, setLikeAction] = useState(null);
  const [dislikeAction, setDislikeAction] = useState(null);

  let uri, params;

  if (props.video) {
    uri = `${BACKEND_URL}/api/video/like`;
    params = { videoId: props.videoId, userId: props.userId };
  } else {
    uri = `${BACKEND_URL}/api/comment/like`;
    params = { commentId: props.commentId, userId: props.userId };
  }

  useEffect(() => {
    axios
      .get(uri, { params })
      .then((res) => {
        const { likeCount, dislikeCount, userAction } = res.data;
        setLikeCount(likeCount);
        setDislikeCount(dislikeCount);

        setLikeAction(userAction === "like" ? "selected" : null);
        setDislikeAction(userAction === "dislike" ? "selected" : null);
      })
      .catch((error) => {
        console.error(error);
        alert("서버 오류가 발생하였습니다.");
      });
  }, []);

  const handleLike = (action) => {
    const payload = { ...params, action };

    axios.post(uri, payload).then(() => {
      if (action === "like") {
        setLikeCount(likeAction ? likeCount - 1 : likeCount + 1);
        setLikeAction(likeAction ? null : "selected");

        if (dislikeAction) {
          setDislikeCount(dislikeCount - 1);
          setDislikeAction(null);
        }
      } else {
        setDislikeCount(dislikeAction ? dislikeCount - 1 : dislikeCount + 1);
        setDislikeAction(dislikeAction ? null : "selected");

        if (likeAction) {
          setLikeCount(likeCount - 1);
          setLikeAction(null);
        }
      }
    });
  };

  return (
    <>
      <span key="comment-basic-like">
        <Tooltip title="Like">
          <LikeOutlined
            style={{
              fontSize: "24px",
              color: likeAction === "selected" ? "blue" : "gray",
            }}
            onClick={() => handleLike("like")}
          />
        </Tooltip>
        <span style={{ paddingLeft: "8px", cursor: "auto" }}>{likeCount}</span>
      </span>
      &nbsp;&nbsp;
      <span key="comment-basic-dislike">
        <Tooltip title="Dislike">
          <DislikeOutlined
            style={{
              fontSize: "24px",
              color: dislikeAction === "selected" ? "red" : "gray",
            }}
            onClick={() => handleLike("dislike")}
          />
        </Tooltip>
        <span style={{ paddingLeft: "8px", cursor: "auto" }}>
          {dislikeCount}
        </span>
      </span>
    </>
  );
}

export default LikeDislikes;
