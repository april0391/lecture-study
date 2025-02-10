import { Link } from "react-router-dom";

const ButtonLink = ({ uri, text }) => {
  return (
    <Link
      className="px-6 py-3 bg-blue-500 text-white font-semibold rounded-lg shadow-lg hover:bg-blue-600 transition duration-300"
      to={uri}
    >
      {text}
    </Link>
  );
};

export default ButtonLink;
