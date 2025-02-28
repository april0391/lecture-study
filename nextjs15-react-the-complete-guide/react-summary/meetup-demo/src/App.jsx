import { Routes, Route } from "react-router-dom";
import { FavoritesContextProvider } from "./store/FavoritesContext.jsx";
import AllMeetups from "./pages/AllMeetups";
import NewMeetup from "./pages/NewMeetup";
import Favorites from "./pages/Favorites";
import Layout from "./components/layout/Layout";

function App() {
  return (
    <FavoritesContextProvider>
      <Layout>
        <Routes>
          <Route path="/" element={<AllMeetups />} />
          <Route path="/new-meetup" element={<NewMeetup />} />
          <Route path="/favorites" element={<Favorites />} />
          <Route path="*" element={<h1>404</h1>} />
        </Routes>
      </Layout>
    </FavoritesContextProvider>
  );
}

export default App;
