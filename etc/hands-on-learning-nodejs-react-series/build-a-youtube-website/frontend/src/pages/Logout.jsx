import { useEffect } from "react";
import { Navigate } from "react-router-dom";

const Logout = () => {
  useEffect(() => {
    localStorage.removeItem("token");
    localStorage.removeItem("userId");
    alert("로그아웃 되었습니다.");
  }, []);

  return <Navigate to={"/"} replace />;
};

export default Logout;
