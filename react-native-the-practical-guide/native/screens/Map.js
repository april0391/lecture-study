import { useCallback, useLayoutEffect, useState } from "react";
import { Alert, StyleSheet, View } from "react-native";
import MapView, { Marker, PROVIDER_GOOGLE } from "react-native-maps";

import IconButton from "../components/ui/IconButton";

export default function Map({ navigation }) {
  const [selectedLocation, setSelectedLocation] = useState({
    latitude: 37.78,
    longitude: -122.43,
  });

  console.log("selectedLocation", selectedLocation);

  useLayoutEffect(() => {
    navigation.setOptions({
      headerRight: ({ tintColor }) => (
        <IconButton
          icon="save"
          size={24}
          color={tintColor}
          onPress={savePickedLocationHandler}
        />
      ),
    });
  }, [navigation, selectedLocation]);

  const region = {
    latitude: 37.5665,
    longitude: 126.978,
    latitudeDelta: 0.005,
    longitudeDelta: 0.005,
  };

  function selectLocationHandler(event) {
    // console.log("event", event);
    const latitude = event.nativeEvent.coordinate.latitude;
    const longitude = event.nativeEvent.coordinate.longitude;

    setSelectedLocation({ latitude, longitude });
  }

  const savePickedLocationHandler = useCallback(() => {
    if (!selectedLocation) {
      Alert.alert("No location picked!", "You have to pick a location first!");
      return;
    }

    navigation.navigate("AddPlace", {
      pickedLatitude: selectedLocation.latitude,
      pickedLongitude: selectedLocation.longitude,
    });
  }, [navigation, selectedLocation]);

  return (
    <View style={styles.container}>
      <MapView
        style={styles.map}
        initialRegion={region}
        onPress={selectLocationHandler}
        provider={PROVIDER_GOOGLE}
      >
        {selectedLocation && (
          <Marker
            title="Picked Location"
            coordinate={{
              latitude: selectedLocation.latitude,
              longitude: selectedLocation.longitude,
            }}
          ></Marker>
        )}
      </MapView>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  map: {
    width: "100%",
    height: "100%",
  },
});
