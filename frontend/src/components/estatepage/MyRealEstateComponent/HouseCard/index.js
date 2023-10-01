import React from "react";
import { View } from "react-native";
import * as S from "./style";
import HouseImg from "../../../../assets/House.png";
import SmallButton from "../../../buttons/SmallButton";
const HouseCard = (props) => {
  return (
    <S.Container height={props.height}>
      <S.ImgBox source={HouseImg} />
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
          <SmallButton title="상세보기" bgColor="#0046FF" realtyId={props.realtyId}/>
        </View>
      </S.ContentContainer>
    </S.Container>
  );
};

export default HouseCard;
