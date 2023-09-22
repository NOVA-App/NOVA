import React from "react";
import { Dimensions, View, Text, Image } from "react-native";
import * as S from "./style";
import SmallButton from "../../buttons/SmallButton";

const { width, height } = Dimensions.get("window");

// 버튼 이름, 눌릴 때 함수, 배경 넣기
const AnnualAsset = (props) => {
  return (
    <S.Container height={height} width={width} style={{ marginTop: "3%" }}>
      <S.BoxContainer height={height}>
        <S.BarContainer height={height}>
          <S.PosessionBox height={height} bgColor="#0046FF">
            <Text style={{ color: "white" }}>여유자금</Text>
          </S.PosessionBox>
          <S.PosessionBox height={height} bgColor="#6E67E7">
            <Text style={{ color: "white" }}>생활비</Text>
          </S.PosessionBox>
          <S.PosessionBox height={height} bgColor="#F96464">
            <Text style={{ color: "white" }}>고정지출+월세</Text>
          </S.PosessionBox>
        </S.BarContainer>
        <SmallButton title="· · ·" bgColor="#E7F7F6" fontColor="black" />
      </S.BoxContainer>
    </S.Container>
  );
};

export default AnnualAsset;
