import Link from "next/link";

const page = () => {
  return (
    <main>
      <h1>Blog</h1>
      <p>
        <Link href="/blog/post-1">Link</Link>
      </p>
      <p>
        <Link href="/blog/post-1">Link</Link>
      </p>
    </main>
  );
};

export default page;
