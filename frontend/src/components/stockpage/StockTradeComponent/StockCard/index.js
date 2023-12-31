import React from "react";
import { View, Text } from "react-native";
import * as S from "./style";
import SmallButton from "../../../buttons/SmallButton";
import { useNavigation } from "@react-navigation/native";

const StockCard = (props) => {
  const navigation = useNavigation();

  const handleBuyButtonPress = () => {
    navigation.navigate("StockDetailPage", {
      screen: "StockDetailPage",
      stockId: props.stockItem.stockId,
      rate: props.fluctuations,
    });
  };

  return (
    <S.Container>
      <S.ContentContainer>
        <S.MiddleText>{props.stockName}</S.MiddleText>
        <S.MiddleText>
          {[props.stockAmount].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")}
        </S.MiddleText>
        <Text style={{ fontSize: 25, color: "#D90452" }}>
          {props.fluctuations < 0
            ? props.fluctuations
            : `+${props.fluctuations}`}
          %
        </Text>
      </S.ContentContainer>
      <View
        style={{
          marginTop: "2%",
          flexDirection: "row",
          justifyContent: "flex-end",
          minWidth: "100%",
        }}
      >
        <SmallButton
          title="매수하기"
          bgColor="#0046FF"
          onPress={handleBuyButtonPress}
        />
      </View>
    </S.Container>
  );
};

export default StockCard;