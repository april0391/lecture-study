import { useState } from "react";
import { Routes, Route, Link } from "react-router-dom";
import Home from "./pages/Home";
import Signup from "./pages/Signup";
import Login from "./pages/Login";
import "./App.css";
import UpperNavigation from "./components/UpperNavigation";
import LandingPage from "./pages/landing/LandingPage";
import MovieDetailPage from "./pages/MovieDetail/MovieDetail";

function App() {
  return (
    <>
      <UpperNavigation />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/login" element={<Login />} />
        <Route path="/landing" element={<LandingPage />} />
        <Route path="/movie/:id" element={<MovieDetailPage />} />
      </Routes>
    </>
  );
}

export default App;
