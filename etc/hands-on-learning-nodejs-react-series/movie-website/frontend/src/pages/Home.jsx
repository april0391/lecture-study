import React from "react";
import { useNavigate } from "react-router-dom";

const Home = () => {
  const navigate = useNavigate();
  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100 p-6">
      <h1 className="text-3xl font-bold text-gray-800 mb-6">Welcome</h1>
      <div className="space-y-4 w-full max-w-xs">
        <button
          onClick={() => navigate("/signup")}
          className="w-full bg-blue-500 text-white py-3 rounded-lg font-semibold text-lg shadow-md hover:bg-blue-600 transition duration-300 cursor-pointer"
        >
          회원가입
        </button>
        <button
          onClick={() => navigate("/login")}
          className="w-full bg-green-500 text-white py-3 rounded-lg font-semibold text-lg shadow-md hover:bg-green-600 transition duration-300 cursor-pointer"
        >
          로그인
        </button>
      </div>
    </div>
  );
};

export default Home;
