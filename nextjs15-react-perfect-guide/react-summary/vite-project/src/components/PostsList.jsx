import Post from "./Post";
import NewPost from "./NewPost";
import Modal from "./Modal";
import classes from "./PostsList.module.css";
import { useState } from "react";

const mockData = [
  <Post key={1} author={"john"} body={"good"} />,
  <Post key={2} author={"alice"} body={"hi"} />,
];

const PostList = ({ isPosting, onStopPosting }) => {
  const [posts, setPosts] = useState(mockData);

  return (
    <>
      {isPosting && (
        <Modal onClose={onStopPosting}>
          <NewPost posts={posts} setPosts={setPosts} />
        </Modal>
      )}

      <ul className={classes.posts}>{posts.map((post) => post)}</ul>
    </>
  );
};

export default PostList;
