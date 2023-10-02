import React from "react";
import { View, Image, TouchableOpacity } from "react-native";
import AgeBar from "../../../../../../../../components/mainpage/AgeBar";
import AnnualAsset from "../../../../../../../../components/mainpage/AnnualAsset";
import MyAsset from "../../../../../../../../components/mainpage/MyAsset";
import { style } from "./style";
import BabyButton from "../../../../../../../../components/buttons/EventButton/BabyButton";
import MarriageButton from "../../../../../../../../components/buttons/EventButton/MarriageButton";
import { useNavigation } from "@react-navigation/native";
import { isChildBirthState } from "../../../../../../../../recoil/recoil";
import { useRecoilState } from "recoil";

const MainComponents = () => {
  const navigation = useNavigation();
  const handleBabyButtonClick = () => {
    navigation.navigate("EventPage", { screen: "ChildPage" });
  };
  const handleMarriageButtonClick = () => {
    navigation.navigate("EventPage", { screen: "MarriagePage" });
  };

  const [isChildBirth] = useRecoilState(isChildBirthState);
  console.log(isChildBirth);
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
            <TouchableOpacity style={{ position: "relative" }}>
              <MarriageButton onPress={handleMarriageButtonClick} />
            </TouchableOpacity>
            <View style={{ marginVertical: 15 }}>
              <TouchableOpacity style={{ position: "relative" }}>
                <BabyButton onPress={handleBabyButtonClick} />
              </TouchableOpacity>
            </View>
          </View>
        </View>
      </View>
    </View>
  );
};

export default MainComponents;
