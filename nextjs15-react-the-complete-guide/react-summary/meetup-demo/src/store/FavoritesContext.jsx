import { createContext, useState } from "react";

const FavoritesContext = createContext({
  favorites: [],
  totalFavorites: 0,
  addFavorite: (favoriteMeetup) => {},
  removeFavorite: (meetupId) => {},
  itemIsFavorite: (meetupId) => {},
});

export const FavoritesContextProvider = ({ children }) => {
  const [userFavorites, setUserFavorites] = useState([]);
  const context = {
    favorites: userFavorites,
    totalFavorites: userFavorites.length,
    addFavorite: (favoriteMeetup) => {
      setUserFavorites((prevUserFavorites) =>
        prevUserFavorites.concat(favoriteMeetup)
      );
    },
    removeFavorite: (meetupId) => {
      setUserFavorites((prevUserFavorites) =>
        prevUserFavorites.filter((meetup) => meetup.id !== meetupId)
      );
    },
    itemIsFavorite: (meetupId) =>
      userFavorites.some((meetup) => meetup.id === meetupId),
  };

  return (
    <FavoritesContext.Provider value={context}>
      {children}
    </FavoritesContext.Provider>
  );
};

export default FavoritesContext;
