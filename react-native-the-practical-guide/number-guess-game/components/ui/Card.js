import { StyleSheet, View } from "react-native";

import Colors from "../../constants/colors";

export default function Card({ children }) {
  return <View style={style.inputContainer}>{children}</View>;
}

const style = StyleSheet.create({
  inputContainer: {
    justifyContent: "center",
    alignItems: "center",
    marginTop: 36,
    marginHorizontal: 24,
    borderRadius: 8,
    padding: 16,
    backgroundColor: Colors.primary800,

    elevation: 4,
    shadowColor: "black",
    shadowOffset: { width: 0, height: 2 },
    shadowRadius: 6,
    shadowOpacity: 0.25,
  },
});
