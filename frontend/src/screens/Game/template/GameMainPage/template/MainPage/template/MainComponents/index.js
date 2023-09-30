import React from "react";
import { View, Text, Image } from "react-native";
import AgeBar from "../../../../../../../../components/mainpage/AgeBar";
import AnnualAsset from "../../../../../../../../components/mainpage/AnnualAsset";
import MyAsset from "../../../../../../../../components/mainpage/MyAsset";
import { style } from "./style";
import BabyButton from "../../../../../../../../components/buttons/EventButton/BabyButton";
import MarriageButton from "../../../../../../../../components/buttons/EventButton/MarriageButton";
const MainComponents = () => {
  return (
    <View style={style.container}>
      <AgeBar />
      <AnnualAsset />
      <MyAsset />
      <View style={style.imageContainer}>
        <View style={style.imageAndButtonContainer}>
          <Image
            source={{
              uri: "https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/People/Man%20Raising%20Hand.png",
            }}
            style={style.image}
          />
          <View style={style.buttonContainer}>
            <MarriageButton />
            <View style={{ marginVertical: 15 }}>
              <BabyButton />
            </View>
          </View>
        </View>
      </View>
    </View>
  );
};

export default MainComponents;
