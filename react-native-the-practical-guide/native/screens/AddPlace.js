import { insertPlace } from "../utils/database";

import PlaceForm from "../components/Places/PlaceForm";

export default function AddPlace({ navigation }) {
  async function createPlaceHandler(place) {
    console.log("before insert");
    await insertPlace(place);
    console.log("after inserted");
    navigation.navigate("AllPlaces");
  }

  return <PlaceForm onCreatePlace={createPlaceHandler} />;
}
