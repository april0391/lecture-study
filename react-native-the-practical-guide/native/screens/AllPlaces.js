import { useEffect, useState } from "react";
import { useIsFocused } from "@react-navigation/native";

import PlacesList from "../components/Places/PlacesList";
import { fetchPlaces } from "../utils/database";

export default function AllPlaces() {
  const [loadedPlace, setLoadedPlace] = useState([]);
  const isFocused = useIsFocused();

  useEffect(() => {
    async function loadPlaces() {
      if (isFocused) {
        const places = await fetchPlaces();
        setLoadedPlace(places);
      }
    }

    loadPlaces();
  }, [isFocused]);

  return <PlacesList places={loadedPlace} />;
}
