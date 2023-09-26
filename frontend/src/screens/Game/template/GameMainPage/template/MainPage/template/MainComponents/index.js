import React from "react";
import { View, Text } from "react-native";
import AgeBar from "../../../../../../../../components/mainpage/AgeBar";
import AnnualAsset from "../../../../../../../../components/mainpage/AnnualAsset";
import MyAsset from "../../../../../../../../components/mainpage/MyAsset";

const MainComponents = () => {
  return (
    <View>
      <Text>hello</Text>
      <AgeBar />
      <AnnualAsset />
      <MyAsset />
    </View>
  );
};

export default MainComponents;
