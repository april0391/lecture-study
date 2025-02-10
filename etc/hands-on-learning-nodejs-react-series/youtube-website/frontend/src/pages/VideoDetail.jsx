import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { List, Avatar, Row, Col } from "antd";
import axios from "axios";
import SideVideo from "./sections/SideVideo";
import Subscription from "./sections/Subscription";
import Comments from "./sections/Comments";
import LikeDislikes from "./sections/LikeDislikes";

const BACKEND_URL = import.meta.env.VITE_BACKEND_URL;

function VideoDetail() {
  const { id } = useParams();
  const [video, setVideo] = useState();
  const [commentLists, setCommentLists] = useState([]);

  const videoVariable = {
    videoId: id,
  };

  useEffect(() => {
    axios
      .post(`${BACKEND_URL}/api/video/getVideo`, videoVariable)
      .then((res) => {
        if (res.data) {
          setVideo(res.data);
        } else {
          alert("Failed to get video Info");
        }
      });

    axios
      .post(`${BACKEND_URL}/api/comment/getComments`, videoVariable)
      .then((res) => {
        if (res.data) {
          setCommentLists(res.data);
        } else {
          alert("Failed to get comment");
        }
      });
  }, []);

  const updateComment = (newComment) => {
    setCommentLists(commentLists.concat(newComment));
  };

  if (video) {
    return (
      <Row>
        <Col lg={18} xs={24}>
          <div
            className="postPage"
            style={{ width: "100%", padding: "3rem 4em" }}
          >
            <video
              style={{ width: "100%" }}
              src={`${BACKEND_URL}/${video.filePath}`}
              controls
            ></video>

            <List.Item
              actions={[
                <LikeDislikes
                  key={id}
                  videoId={id}
                  userId={localStorage.getItem("userId")}
                />,
                <Subscription
                  key={video.writer._id}
                  userTo={video.writer._id}
                  userFrom={localStorage.getItem("userId")}
                />,
              ]}
            >
              <List.Item.Meta
                avatar={<Avatar src={video.writer && video.writer.image} />}
                title={video.title}
                description={
                  <>
                    {video.description}
                    <br />
                    <span>{video.writer && video.writer.username}</span>
                  </>
                }
              />
              <div></div>
            </List.Item>

            <Comments
              commentLists={commentLists}
              postId={video._id}
              refreshFunction={updateComment}
            />
          </div>
        </Col>
        <Col lg={6} xs={24}>
          <SideVideo />
        </Col>
      </Row>
    );
  } else {
    return <div>Loading...</div>;
  }
}

export default VideoDetail;
