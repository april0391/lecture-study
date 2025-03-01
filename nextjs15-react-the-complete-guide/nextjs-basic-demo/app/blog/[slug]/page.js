import React from "react";

const BlogPostPage = ({ params }) => {
  return (
    <main>
      <h1>Blog Post</h1>
      {params.slug}
    </main>
  );
};

export default BlogPostPage;
