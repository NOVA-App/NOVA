import React from "react";
import { Dimensions, View, Text } from "react-native";
import * as S from "./style";

const { width, height } = Dimensions.get("window");


// 버튼 이름, 눌릴 때 함수, 배경 넣기
const Button = (props) => {
  return (
    <S.ButtonContainer
      onPress={props.onPress}
      pressRetentionOffset={{ bottom: 10, top: 10, left: 10, right: 10 }}
      bgColor={props.bgColor}
      height={height}
      width={width}
    >
      <View>
        <Text style={{ fontSize: 15, color: props.fontColor || "white" }}>
          {props.title}
        </Text>
      </View>
    </S.ButtonContainer>
  );
};

export default Button;
