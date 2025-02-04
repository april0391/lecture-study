import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const Login = () => {
  const [formData, setFormData] = useState({
    email: "",
    password: "",
  });
  const navigate = useNavigate();

  const handleOnChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleLogin = async () => {
    try {
      const { data } = await axios.post(
        `${import.meta.env.VITE_BACKEND_URL}/login`,
        formData
      );
      localStorage.setItem("token", data.token);
      alert("로그인에 성공하였습니다.");
      navigate("/");
    } catch (error) {
      console.error("로그인 실패:", error.response?.data || error.message);
      alert("이메일과 비밀번호를 확인해주세요.");
    }
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-gray-100">
      <div className="bg-white p-8 rounded-2xl shadow-lg w-96">
        <h2 className="text-2xl font-semibold text-center text-gray-800 mb-6">
          로그인
        </h2>
        <div className="space-y-4">
          <input
            type="text"
            name="email"
            value={formData.email}
            onChange={handleOnChange}
            placeholder="이메일"
            className="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
          />
          <input
            type="password"
            name="password"
            value={formData.password}
            onChange={handleOnChange}
            placeholder="비밀번호"
            className="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
          />
          <button
            onClick={handleLogin}
            className="w-full bg-blue-500 text-white py-2 rounded-lg hover:bg-blue-600 transition"
          >
            로그인
          </button>
        </div>
      </div>
    </div>
  );
};

export default Login;
