import React, { useState } from "react";
import { Text, StyleSheet, View, TouchableOpacity } from "react-native";
import TabSelect from "./TabSelect";
import styled from "styled-components/native";

const StockMainPage = () => {
  const [selectedTab, setSelectedTab] = useState(0);

  const changeTabFirst = () => {
    setSelectedTab(0);
  };
  const changeTabSecond = () => {
    setSelectedTab(1);
  };

  return (
    <View style={styles.container}>
      <View style={styles.tab}>
        <View style={styles.textContainer}>
          <TouchableOpacity onPress={changeTabFirst}>
            <Text
              style={{
                fontSize: 20,
                fontWeight: selectedTab === 0 ? "bold" : "light",
              }}
            >
              보유현황
            </Text>
          </TouchableOpacity>
        </View>
        <View style={styles.textContainer}>
          <TouchableOpacity onPress={changeTabSecond}>
            <Text
              style={{
                fontSize: 20,
                fontWeight: selectedTab === 1 ? "bold" : "light",
              }}
            >
              주식 거래
            </Text>
          </TouchableOpacity>
        </View>
      </View>
      <View style={styles.lineStyle} />
      <View style={styles.content}>
        <TabSelect selectedTab={selectedTab} />
      </View>
    </View>
  );
};

export { StockMainPage };

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    backgroundColor: "#CDE8E5",
    flexDirection: "column",
  },
  tab: {
    flex: 0.7,
    flexDirection: "row",
    justifyContent: "space-evenly",
    alignItems: "center",
    backgroundColor: "#CDE8E5",
  },
  textContainer: {
    flex: 1,
    alignItems: "center",
  },
  content: {
    flex: 8,
  },
  lineStyle: {
    borderWidth: 0.5,
    width: 600,
    borderColor: "white",
    marginBottom: 0,
  },
});
