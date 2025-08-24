// import { insertPlace } from "../utils/database";

import PlaceForm from "../components/Places/PlaceForm";

export default function AddPlace({ navigation }) {
  async function createPlaceHandler(place) {
    // await insertPlace(place);
    navigation.navigate("AllPlaces", {
      place,
    });
  }

  return <PlaceForm onCreatePlace={createPlaceHandler} />;
}
