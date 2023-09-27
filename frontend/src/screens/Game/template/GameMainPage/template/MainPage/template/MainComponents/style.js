import { StyleSheet } from "react-native";

export const style = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
  },
  imageContainer: {
    width: 300,
    height: 300,
    justifyContent: "center",
    alignItems: "center",
  },
  imageAndButtonContainer: {
    flexDirection: "row",
    alignItems: "center",
  },
  image: {
    width: 250, // 이미지의 너비를 조절하거나 필요에 따라 변경
    height: 250, // 이미지의 높이를 조절하거나 필요에 따라 변경
  },
});
