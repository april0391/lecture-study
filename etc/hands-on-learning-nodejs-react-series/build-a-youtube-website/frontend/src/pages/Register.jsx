import { useRef, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const formFields = ["email", "password", "username"];

const Register = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState(
    formFields.reduce((acc, field) => {
      acc[field] = "";
    }, {})
  );
  const inputRefs = useRef(
    formFields.reduce((acc, field) => {
      acc[field] = null;
    }, {})
  );

  const inputs = [
    {
      name: "email",
      type: "text",
      placeholder: "이메일을 입력하세요",
    },
    {
      name: "password",
      type: "password",
      placeholder: "비밀번호를 입력하세요",
    },
    {
      name: "username",
      type: "text",
      placeholder: "닉네임을 입력하세요",
    },
  ];

  const handleOnChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = async () => {
    console.log(inputRefs.current);

    /* try {
      await axios.post(`${import.meta.env.VITE_BACKEND_URL}/users`, formData);
      alert("회원가입에 성공하였습니다.");
      navigate("/");
    } catch (error) {
      console.log(error);
    } */
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-gray-100">
      <div className="bg-white p-8 rounded-lg shadow-md w-96">
        <h2 className="text-2xl font-bold text-center text-gray-700 mb-6">
          회원가입
        </h2>
        <div className="space-y-4">
          {inputs.map((input) => (
            <input
              key={input.name}
              type={input.type}
              name={input.name}
              value={formData[input.name]}
              placeholder={input.placeholder}
              onChange={handleOnChange}
              ref={(el) => (inputRefs.current[input.name] = el)}
              className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
            />
          ))}
          <button
            onClick={handleSubmit}
            className="w-full bg-blue-500 text-white py-2 rounded-lg hover:bg-blue-600 transition"
          >
            회원가입
          </button>
        </div>
      </div>
    </div>
  );
};

export default Register;
