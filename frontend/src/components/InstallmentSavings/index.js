import React from "react";
import { View, Text, ScrollView, Image, Dimensions } from "react-native";
import * as S from "./style";
import SmallButton from "../buttons/SmallButton/index"
import InputSmall from "../input/SmallInput";

const { height, width } = Dimensions.get("window");

const InstallmentSavingsCard = (props) => {
  return (
    <S.Container height={height * 0.4} >

        <S.TagContainer >
          <Text style={{ fontSize: 20, color: "white" }}>적금</Text>
        </S.TagContainer>

        <S.SmallContainer>
          <S.MiddleText>적금 이름</S.MiddleText>
            <InputSmall height={props.height}/>
        </S.SmallContainer >
        
        <S.SmallContainer>
        <S.MiddleText>적금 기간</S.MiddleText>
            <InputSmall height={props.height}/>
        </S.SmallContainer>
        <S.SmallContainer>
        <S.MiddleText>연 납입 금액</S.MiddleText>
            <InputSmall height={props.height}/>
        </S.SmallContainer>

        
        
        <View
          style={{
            marginTop: "2%",
            flexDirection: "row",
            justifyContent: "flex-end",
          }}
        >
          <SmallButton title="가입하기" bgColor="#0046FF" />
          {/* <Text>fdfd</Text> */}
        </View>
    </S.Container>
  );
};

export default InstallmentSavingsCard;
