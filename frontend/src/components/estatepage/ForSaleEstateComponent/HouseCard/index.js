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
          <SmallButton title="매수하기" bgColor="#0046FF" />
        </View>
      </S.ContentContainer>
    </S.Container>
  );
};

export default HouseCard;
