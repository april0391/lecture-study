import React, { useEffect, useState } from "react";
import axios from "axios";
import { BACKEND_URL } from "../../config/constants";

function SideVideo() {
  const [sideVideos, setSideVideos] = useState([]);

  useEffect(() => {
    axios.get(`${BACKEND_URL}/api/video/getVideos`).then((res) => {
      if (res.data) {
        setSideVideos(res.data);
      } else {
        alert("Failed to get Videos");
      }
    });
  }, []);

  const sideVideoItem = sideVideos.map((video) => {
    var minutes = Math.floor(video.duration / 60);
    var seconds = Math.floor(video.duration - minutes * 60);

    return (
      <div
        key={video._id}
        style={{ display: "flex", marginTop: "1rem", padding: "0 2rem" }}
      >
        <div style={{ width: "40%", marginRight: "1rem" }}>
          <a href={`/video/${video._id}`} style={{ color: "gray" }}>
            <img
              style={{ width: "100%" }}
              src={`${BACKEND_URL}/${video.thumbnail}`}
              alt="thumbnail"
            />
          </a>
        </div>

        <div style={{ width: "50%" }}>
          <a href={`/video/${video._id}`} style={{ color: "gray" }}>
            <span style={{ fontSize: "1rem", color: "black" }}>
              {video.title}{" "}
            </span>
            <br />
            <span>{video.writer.name}</span>
            <br />
            <span>{video.views}</span>
            <br />
            <span>
              {minutes} : {seconds}
            </span>
            <br />
          </a>
        </div>
      </div>
    );
  });

  return (
    <React.Fragment>
      <div style={{ marginTop: "3rem" }}></div>
      {sideVideoItem}
    </React.Fragment>
  );
}

export default SideVideo;
