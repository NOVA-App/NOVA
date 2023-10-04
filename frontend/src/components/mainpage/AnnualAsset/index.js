import React, { useState } from "react";
import { Dimensions, View, Text, Image } from "react-native";
import * as S from "./style";
import SmallButton from "../../buttons/SmallButton";
import { annualModalState } from "../../../recoil/recoil";
import { useRecoilState } from "recoil";

const { width, height } = Dimensions.get("window");

const AnnualAsset = (props) => {
  const [, setModalVisible] = useRecoilState(annualModalState);

  const openModal = () => {
    setModalVisible(true);
  };

  return (
    <S.Container height={height} width={width} style={{ marginTop: "3%" }}>
      <S.BoxContainer height={height}>
        <S.BarContainer height={height}>
          <S.PosessionBox height={height} bgColor="#0046FF">
            <Text style={{ color: "white" }}>여유자금</Text>
            <Text style={{ color: "white" }}>{props.asset.usableAsset}</Text>
          </S.PosessionBox>
          <S.PosessionBox height={height} bgColor="#6E67E7">
            <Text style={{ color: "white" }}>생활비</Text>
            <Text style={{ color: "white" }}>{props.asset.livingCost}</Text>
          </S.PosessionBox>
          <S.PosessionBox height={height} bgColor="#F96464">
            <Text style={{ color: "white" }}>고정지출+월세</Text>
            <Text style={{ color: "white" }}>
              {props.asset.fixedCost.totalFixedCost}
            </Text>
          </S.PosessionBox>
        </S.BarContainer>
        <SmallButton
          title="· · ·"
          bgColor="#E7F7F6"
          fontColor="black"
          onPress={openModal}
        />
      </S.BoxContainer>
    </S.Container>
  );
};

export default AnnualAsset;
