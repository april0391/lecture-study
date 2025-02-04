import { Routes, Route } from "react-router-dom";
import "./App.css";
import Home from "./pages/Home";
import Login from "./pages/Login";

import ProtectedRoute from "./components/ProtectedRoute";
import VideoUpload from "./pages/VideoUpload";
import Logout from "./pages/Logout";
import Dashboard from "./pages/temp/Dashboard";
import Dashboard2 from "./pages/temp/Dashboard2";

function App() {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/login" element={<Login />} />
      <Route path="/dashboard" element={<Dashboard />} />
      <Route path="/dashboard2" element={<Dashboard2 />} />

      <Route element={<ProtectedRoute />}>
        <Route path="/logout" element={<Logout />} />
        <Route path="/video/upload" element={<VideoUpload />} />
      </Route>
    </Routes>
  );
}

export default App;
