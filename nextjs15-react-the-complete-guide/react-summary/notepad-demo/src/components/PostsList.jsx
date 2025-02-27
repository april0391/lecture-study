import Post from "./Post";
import NewPost from "./NewPost";
import Modal from "./Modal";
import classes from "./PostsList.module.css";
import { useState, useEffect } from "react";

const PostList = ({ isPosting, onStopPosting }) => {
  const [posts, setPosts] = useState([]);
  const [isFetching, setIsFetching] = useState(false);
  useEffect(() => {
    const fetchPosts = async () => {
      setIsFetching(true);
      const res = await fetch("http://localhost:8080/posts");
      const data = await res.json();
      setPosts(data.posts);
      setIsFetching(false);
    };
    fetchPosts();
  }, []);
  const addPostHandler = (postData) => {
    fetch("http://localhost:8080/posts", {
      method: "POST",
      body: JSON.stringify(postData),
      headers: {
        "Content-Type": "application/json",
      },
    });
    setPosts((prevPosts) => [...prevPosts, postData]);
  };
  return (
    <>
      {isPosting && (
        <Modal onClose={onStopPosting}>
          <NewPost onCancel={onStopPosting} onAddPost={addPostHandler} />
        </Modal>
      )}
      {!isFetching && posts.length > 0 && (
        <ul className={classes.posts}>
          {posts.map((post, idx) => {
            return <Post key={idx} author={post.author} body={post.body} />;
          })}
        </ul>
      )}
      {!isFetching && posts.length === 0 && <h2>There are no posts yet.</h2>}
      {isFetching && <p>Loading posts...</p>}
    </>
  );
};

export default PostList;
