import { StyleSheet } from "react-native";

const styles = StyleSheet.create({
  imageContainer: {
    flexDirection: "row",
    alignItems: "center",
    marginBottom: 10,
    width: "80%", // Adjust this width as needed
  },
  icon: {
    width: 100,
    height: 100,
    marginRight: 10,
  },
  imageText: {
    fontSize: 18,
    flex: 1, // Let the text take the remaining space
  },
  section: {
    justifyContent: "center",
    alignItems: "center",
    flex: 1,
  },
  marginBottom: {
    marginBottom: 10,
  },
  userInfo: {
    flexDirection: "column",
    alignItems: "center",
  },
  userName: {
    marginBottom: 5,
    color: "#D90452"
  },
  profileImage: {
    width: 50,
    height: 50,
    borderRadius: 25,
    marginBottom: 10,
  },
  tableContainer: {
    flex: 3,
    justifyContent: "center",
    alignItems: "center",
  },
  table: {
    flexDirection: "column",
    borderWidth: 1,
    borderColor: "#000",
    width: "100%",
    borderRadius: 5,
    overflow: "hidden",
  },
  tableHeader: {
    backgroundColor: "#7DE4C0",
  },
  tableRow: {
    flexDirection: "row",
    justifyContent: "space-between",
    borderBottomWidth: 1,
    borderBottomColor: "#000",
    paddingVertical: 8,
  },
  tableCell: {
    flex: 1,
    padding: 10,
    textAlign: "center",
  },
  shareButton: {
    position: "absolute",
    top: 10,
    right: 10,
    zIndex: 1,
  },
  shareIcon: {
    width: 30,
    height: 30,
  },
});

export default styles;