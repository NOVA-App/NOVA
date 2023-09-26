import React from "react";
import { View, Text, Image } from "react-native";
import AgeBar from "../../../../../../../../components/mainpage/AgeBar";
import AnnualAsset from "../../../../../../../../components/mainpage/AnnualAsset";
import MyAsset from "../../../../../../../../components/mainpage/MyAsset";

const MainComponents = () => {
  return (
    <View>
      <AgeBar />
      <AnnualAsset />
      <MyAsset />
      <Image
        source={{ uri: "https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/People/Woman%20Raising%20Hand.png" }}
        style={{ width: 300, height: 300 }}
      />    
      </View>
  );
};

export default MainComponents;
