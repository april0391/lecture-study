import { useState } from "react";
import { Routes, Route } from "react-router-dom";
import PostList from "./components/PostsList";
import MainHeader from "./components/MainHeader";
import NewPost from "./components/NewPost";

function App() {
  const [modalIsVisible, setModalIsVisible] = useState(false);

  function hideModalHandler() {
    setModalIsVisible(false);
  }

  function showModalHandler() {
    setModalIsVisible(true);
  }

  return (
    <>
      <MainHeader onCreatePost={showModalHandler} />
      <Routes>
        <Route
          path="/"
          element={
            <PostList
              isPosting={modalIsVisible}
              onStopPosting={hideModalHandler}
            />
          }
        />
        <Route path="/create-post" element={<NewPost />} />
      </Routes>
    </>
  );
}

export default App;
