import React from "react";
import { Dimensions, View, Text, Image } from "react-native";
import * as S from "./style";

const { width, height } = Dimensions.get("window");

// 버튼 이름, 눌릴 때 함수, 배경 넣기
const BabyButton = (props) => {
  return (
    <S.ButtonContainer
      onPress={props.onPress}
      pressRetentionOffset={{ bottom: 10, top: 10, left: 10, right: 10 }}
      bgColor={props.bgColor}
      height={height}
      width={width}
    >
      <View style={{ flexDirection: "row", alignItems: "center", justifyContent: "center" }}>
        <Image
          source={{
            uri: "https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/People/Baby.png",
          }}
          style={{ width: 45, height: 45, marginRight: 5 }}
        />
        <Text style={{ fontSize: 15, color: props.fontColor || "white" }}>{props.title}</Text>
      </View>
    </S.ButtonContainer>
  );
};

export default BabyButton;
