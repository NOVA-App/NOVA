import React, { useState } from "react";
import { Text, StyleSheet, View, TouchableOpacity } from "react-native";
import Budget from "../../../../components/budget";
import TabSelect from "./TabSelect";
import styled from "styled-components/native";

const RealEstatePage = () => {
  const [selectedTab, setSelectedTab] = useState(1);

  const changeTabFirst = () => {
    setSelectedTab(0);
  };
  const changeTabSecond = () => {
    setSelectedTab(1);
  };
  const changeTabThird = () => {
    setSelectedTab(2);
  };

  return (
    <View style={styles.container}>
      <Budget />
      <View style={styles.tab}>
        <View style={styles.textContainer}>
          <TouchableOpacity onPress={changeTabFirst}>
            <Text
              style={{
                fontSize: 20,
                fontWeight: selectedTab === 0 ? "bold" : "light",
              }}
            >
              내 부동산
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
              매물 목록
            </Text>
          </TouchableOpacity>
        </View>
        <View style={styles.textContainer}>
          <TouchableOpacity onPress={changeTabThird}>
            <Text
              style={{
                fontSize: 20,
                fontWeight: selectedTab === 2 ? "bold" : "light",
              }}
            >
              대출 목록
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

export default RealEstatePage;

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
