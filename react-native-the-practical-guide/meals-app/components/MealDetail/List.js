import { StyleSheet, Text, View } from "react-native";

export default function List({ data }) {
  return data.map((dataPoint) => (
    <View style={styles.listItem}>
      <Text key={dataPoint} style={styles.itemText}>
        {dataPoint}
      </Text>
    </View>
  ));
}

const styles = StyleSheet.create({
  listItem: {
    marginVertical: 8,
    marginHorizontal: 12,
    borderRadius: 6,
    paddingHorizontal: 8,
    paddingVertical: 4,
    backgroundColor: "#e2b497",
  },
  itemText: {
    color: "#351401",
    textAlign: "center",
  },
});
