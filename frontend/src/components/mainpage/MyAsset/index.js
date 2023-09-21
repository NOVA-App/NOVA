import React from "react";
import { Dimensions, View, Text } from "react-native";
import UpSemiCircle from "../UpSemiCircle";
import DownSemiCircle from "../DownSemiCircle";
import * as S from "./style";

const { width, height } = Dimensions.get("window");

// 버튼 이름, 눌릴 때 함수, 배경 넣기
const MyAsset = (props) => {
  return (
    <S.Container style={{ marginTop: "3%" }}>
      <View>
        <UpSemiCircle />
        <DownSemiCircle />
      </View>
      <View>
        <UpSemiCircle />
        <DownSemiCircle />
      </View>
    </S.Container>
  );
};

export default MyAsset;
