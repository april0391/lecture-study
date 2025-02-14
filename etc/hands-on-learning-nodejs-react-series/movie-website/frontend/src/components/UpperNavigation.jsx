import { Link } from "react-router-dom";

const UpperNavigation = () => {
  return (
    <nav className="bg-blue-500 p-4 shadow-md">
      <div className="max-w-7xl mx-auto flex justify-between items-center">
        <div className="text-white text-2xl font-bold">
          <Link to="/">My Website</Link>
        </div>
        <div>
          <ul className="flex space-x-4">
            <li>
              <Link
                to="/"
                className="text-white hover:text-blue-200 transition"
              >
                Home
              </Link>
            </li>
            <li>
              <Link
                to="/signup"
                className="text-white hover:text-blue-200 transition"
              >
                Sign Up
              </Link>
            </li>
            <li>
              <Link
                to="/login"
                className="text-white hover:text-blue-200 transition"
              >
                Login
              </Link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
};

export default UpperNavigation;
