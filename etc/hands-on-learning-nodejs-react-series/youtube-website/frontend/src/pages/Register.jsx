import { useRef, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

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

const validateOrThrow = (formData, inputRefs) => {
  const invalidFields = [];
  for (let field in formData) {
    if (formData[field] === "") {
      invalidFields.push(field);
    }
  }
  if (invalidFields.length !== 0) {
    alert("입력 데이터가 올바르지 않습니다.");
    throw new Error("입력 데이터가 올바르지 않습니다.");
  }
};

const Register = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState(
    inputs.reduce((acc, input) => {
      acc[input.name] = "";
      return acc;
    }, {})
  );
  const inputRefs = useRef(
    inputs.reduce((acc, input) => {
      acc[input.name] = null;
      return acc;
    }, {})
  );

  const handleOnChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = async () => {
    validateOrThrow(formData, inputRefs);
    try {
      await axios.post(`${import.meta.env.VITE_BACKEND_URL}/users`, formData);
      alert("회원가입에 성공하였습니다.");
      navigate("/");
    } catch (error) {
      console.log(error);
    }
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
