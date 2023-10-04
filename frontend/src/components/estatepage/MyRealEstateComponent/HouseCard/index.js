import React, { useState, useEffect } from "react";
import { Dimensions, View, Text, ScrollView } from "react-native";
import * as S from "./style";
import HouseImg from "../../../../assets/House.png";
import SmallButton from "../../../buttons/SmallButton";
import { useNavigation } from "@react-navigation/native";
import { gameIdState } from "../../../../recoil/recoil";
import { useRecoilValue } from "recoil";

const HouseCard = (props) => {
  const navigation = useNavigation();
  const gameID = useRecoilValue(gameIdState);

  const handleHouseDetailPress = () => {
    navigation.navigate("MyRealEstateDetail", {
      screen: "MyRealEstateDetail",
      realtyId: props.realtyId,
    });
  };

  useEffect(() => {
    console.log("props들 확인");
    console.log(props.realtyId);
    console.log(props.realtyName);
    console.log(props.investAmount);
    console.log(props.evaluationAmount);
    console.log(props.rentalIncome);
  }, []);

  return (
    <S.Container height={props.height}>
      <S.ImgBox source={{ uri: props.realtyImg }} />
      <S.ContentContainer>
        <S.TextContainer>
          <S.MiddleText>{`총 투자금`}</S.MiddleText>
          <S.MiddleText>{props.investAmount}</S.MiddleText>
        </S.TextContainer>
        <S.TextContainer>
          <S.MiddleText>{`총 평가금`}</S.MiddleText>
          <S.MiddleText>{props.evaluationAmount}</S.MiddleText>
        </S.TextContainer>
        <S.TextContainer>
          <S.MiddleText>{`월세 수익`}</S.MiddleText>
          <S.MiddleText>{props.rentalIncome}</S.MiddleText>
        </S.TextContainer>
        <View
          style={{
            marginTop: "5%",
            flexDirection: "row",
            justifyContent: "flex-end",
          }}
        >
          <SmallButton
            title="상세보기"
            bgColor="#0046FF"
            realtyId={props.realtyId}
            onPress={handleHouseDetailPress}
          />
        </View>
      </S.ContentContainer>
    </S.Container>
  );
};

export default HouseCard;
