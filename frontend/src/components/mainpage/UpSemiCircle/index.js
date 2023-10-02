import React from "react";
import { Dimensions, View } from "react-native";
import Svg, { Circle, Text } from "react-native-svg";

const UpSemiCircle = (props) => {
  const windowWidth = Dimensions.get("window").width;
  const windowHeight = Dimensions.get("window").height;

  const viewWidth = windowWidth * 0.5; // 화면 너비의 50%
  const viewHeight = windowHeight * 0.12; // 화면 높이의 25%

  return (
    <View
      style={{
        width: viewWidth,
        height: viewHeight,
        backgroundColor: "transparent",
        alignItems: "center",
        overflow: "hidden",
      }}
    >
      <Svg height="90%" width="90%" viewBox="0 0 100 50">
        <Circle
          cx="50"
          cy="50" // 중심을 상단으로 이동시켜 상단의 반만 나오도록 합니다.
          r="50"
          fill={props.bgColor}
        />
        <Circle
          cx="50"
          cy="50" // 두 번째 원의 중심을 조절하여 작은 반원을 만듭니다.
          r="40" // 작은 반원의 반지름 설정
          fill="white" // 작은 반원의 색상 설정
        />
        <Text
          x="50%"
          y="65%" // 텍스트의 위치를 조절합니다.
          textAnchor="middle"
          fill="#51c5cf"
          stroke="black"
          strokeWidth="1"
        >
          {props.title}
        </Text>
        <Text
          x="50%"
          y="90%" // 텍스트의 위치를 조절합니다.
          textAnchor="middle"
          fill="#51c5cf"
          stroke="black"
          strokeWidth="1"
        >
          {props.amount}
        </Text>
      </Svg>
    </View>
  );
};

export default UpSemiCircle;
