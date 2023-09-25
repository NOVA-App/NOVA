import React from "react";
import { View, Text, ScrollView, Image, Dimensions } from "react-native";
import * as S from "./style";
import SmallButton from "../../../../../../components/buttons/SmallButton/index"


const { height, width } = Dimensions.get("window");

const InstallmentSavingsCard = (props) => {
  return (
    <S.Container height={height} >

        <S.SmallContainer>
          <S.MiddleText>적금 이름</S.MiddleText>
          <S.PropsMiddleText>결혼자금</S.PropsMiddleText>
        </S.SmallContainer >
        
        <S.SmallContainer>
        <S.MiddleText>현재금액</S.MiddleText>
        <S.PropsMiddleText>1,000,000,000,000 원</S.PropsMiddleText>
        </S.SmallContainer>

        <S.SmallContainer>
        <S.MiddleText>연 납입 금액</S.MiddleText>
        <S.PropsMiddleText>1,000,000,000,000 원</S.PropsMiddleText>
        </S.SmallContainer>

        
        
        <View
          style={{
            marginTop: "2%",
            flexDirection: "row",
            justifyContent: "flex-end",
          }}
        >
          <SmallButton title="해지하기" bgColor="#D90452" />
          {/* <Text>fdfd</Text> */}
        </View>
    </S.Container>
  );
};

export default InstallmentSavingsCard;
