import React, { useState, useEffect } from "react";
import { View } from "react-native";
import * as S from "./style";
import HouseImg from "../../../../assets/House.png";
import SmallButton from "../../../buttons/SmallButton";
import { useNavigation } from '@react-navigation/native';

const HouseCard = (props) => {
  const navigation = useNavigation();
  const handleBuyHousePress = () => {
    navigation.navigate("ForSaleDetail", { screen: "ForSaleDetail", realtyId: props.realtyId});
  const realtyId = props.realtyId
  };


  return (
    <S.Container height={props.height}>
      <S.ImgBox source={{ uri: props.realtyImg }} />
      <S.ContentContainer>
        <S.TextContainer>
          <S.MiddleText>{props.realtyName}</S.MiddleText>
          <S.PercentText>
            {props.percent < 0 ? props.percent : `+${props.percent}`}%
          </S.PercentText>
        </S.TextContainer>
        <S.TextContainer>
          <S.MiddleText>{`금액`}</S.MiddleText>
          <S.MiddleText>{props.realtyAmount}</S.MiddleText>
        </S.TextContainer>
        <S.TextContainer>
          <S.MiddleText>{`예상 월세 수익`}</S.MiddleText>
          <S.MiddleText>{props.predictIncome}</S.MiddleText>
        </S.TextContainer>
        <View
          style={{
            width: "100%",
            marginTop: "2%",
            flexDirection: "row",
            justifyContent: "flex-end",
          }}
        >
          <SmallButton title="매수하기" bgColor="#0046FF" realtyId={props.realtyId} onPress={handleBuyHousePress}/>
        </View>
      </S.ContentContainer>
    </S.Container>
  );
};

export default HouseCard;
