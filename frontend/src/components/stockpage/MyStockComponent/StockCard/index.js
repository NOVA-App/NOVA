import React from "react";
import { View, Text, TouchableOpacity } from "react-native";
import * as S from "./style";
import SmallButton from "../../../buttons/SmallButton";
import { useNavigation } from "@react-navigation/native";

const StockCard = (props) => {
  const navigation = useNavigation();
  const handleDetailPress = () => {
    navigation.navigate("StockDetailPage", {
      screen: "StockDetailPage",
      stockId: props.stock.stockId,
      rate: props.stock.fluctuationsPercent,
    });
  };
  console.log(props.stock.stockId);

  return (
    <S.Container height={props.height}>
      <S.StockContainer>
        <Text style={{ fontSize: 25 }}>{props.stock.stockName}</Text>
        {/* <Text style={{ fontSize: 25, color: "#D90452" }}>550,000</Text> */}
        <Text style={{ fontSize: 25, color: "#D90452" }}>
          {Math.floor((props.stock.evaluationAmount/props.stock.investAmount) * 100)-100}%
        </Text>
      </S.StockContainer>
      <S.ContentContainer>
        <S.TextContainer>
          <S.MiddleText>{`   현재가`}</S.MiddleText>
          <S.MiddleText>{[props.stock.evaluationAmount].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')}</S.MiddleText>
        </S.TextContainer>
        <S.TextContainer>
          <S.MiddleText>{`보유 수량`}</S.MiddleText>
          <S.MiddleText>{props.stock.quantity}</S.MiddleText>
        </S.TextContainer>
        <S.TextContainer>
          <S.MiddleText>{`   매입가`}</S.MiddleText>
          <S.MiddleText>{[props.stock.investAmount].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')}</S.MiddleText>
        </S.TextContainer>
        <View
          style={{
            marginTop: "2%",
            flexDirection: "row",
            justifyContent: "flex-end",
          }}
        >
          <TouchableOpacity>
            <SmallButton
              title="상세보기"
              bgColor="#0046FF"
              onPress={handleDetailPress}
            />
          </TouchableOpacity>
        </View>
      </S.ContentContainer>
    </S.Container>
  );
};

export default StockCard;
