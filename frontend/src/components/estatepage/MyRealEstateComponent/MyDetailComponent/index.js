import React from "react";
import { Dimensions, View, Text, ScrollView } from "react-native";
import * as S from "./style";
import HouseImg from "../../../../assets/House.png";
import Button from "../../../buttons/SmallButton";
const { height } = Dimensions.get("window");

const MyRealEstateDetail = (props) => {
  return (
    <View style={{ flex: 1 }}>
      <S.Container style={{ flex: 8.5, minWidth: "80%" }}>
        <View
          style={{
            flex: 0.2,
          }}
        ></View>
        <View style={{ flex: 8, alignItems: "center" }}>
          <S.ImgContainer source={HouseImg}></S.ImgContainer>
          <View style={{ marginTop: "5%" }}></View>
          <S.InfoText>
            마당 딸린 43평 주택 ({"  "}
            <S.InfoText style={{ color: "#D90452" }}>+20%</S.InfoText> )
          </S.InfoText>
          <S.InfoText>{`지역    `}서울시 용산구 한남동</S.InfoText>
          <S.InfoText>{`투자 금액    `}150,000,000</S.InfoText>
          <S.InfoText>{`현재가         `}300,000,000</S.InfoText>
          <S.InfoText>
            {`투자 수익률          `}
            <S.InfoText style={{ color: "#D90452" }}> + 110 % </S.InfoText>
          </S.InfoText>
          <S.InfoText>{`월세 수익        `}15,000,000</S.InfoText>
          <S.InfoText>{`남은 대출금     `}15,000,000</S.InfoText>
        </View>
        <View
          style={{
            marginBottom: "5%",
            marginRight: "5%",
            alignItems: "flex-end",
          }}
        >
          <Button title="매도하기" bgColor="#D90452" />
        </View>
      </S.Container>
    </View>
  );
};

export default MyRealEstateDetail;
