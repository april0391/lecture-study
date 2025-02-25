import classes from "./NewPost.module.css";
import Post from "./Post";

function NewPost({ posts, setPosts }) {
  const changeBodyHandler = (e) => {};
  const handleSubmit = () => {
    const body = document.getElementById("body").value;
    const name = document.getElementById("name").value;
    const newPost = <Post key={posts.length + 1} author={name} body={body} />;
    setPosts((posts) => [...posts, newPost]);
  };

  return (
    <form className={classes.form}>
      <p>
        <label htmlFor="body">Text</label>
        <textarea id="body" required rows={3} onChange={changeBodyHandler} />
      </p>
      <p>
        <label htmlFor="name">Your name</label>
        <input type="text" id="name" required />
      </p>
      <button type="button" onClick={handleSubmit}>
        Add Post
      </button>
    </form>
  );
}

export default NewPost;
