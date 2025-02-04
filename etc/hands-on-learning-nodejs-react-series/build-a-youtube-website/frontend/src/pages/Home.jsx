import ButtonLink from "../components/ButtonLink";

const Home = () => {
  const token = localStorage.getItem("token");

  return (
    <div className="flex flex-col items-center justify-center h-screen bg-gray-100 gap-4">
      {!token ? (
        <ButtonLink text="로그인" uri="/login" />
      ) : (
        <>
          <ButtonLink text="로그아웃" uri="/logout" />
          <ButtonLink text="동영상 업로드" uri="/video/upload" />
        </>
      )}
    </div>
  );
};

export default Home;
