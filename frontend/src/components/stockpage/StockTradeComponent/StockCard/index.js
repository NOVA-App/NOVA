import React from "react";
import { View, Text } from "react-native";
import * as S from "./style";
import SmallButton from "../../../buttons/SmallButton";

const StockCard = (props) => {
  const rate = Math.floor(
    (props.fluctuations * 100) / (props.stockAmount - props.fluctuations)
  );
  return (
    <S.Container height={props.height}>
      <S.ContentContainer>
        <S.MiddleText>{props.stockName}</S.MiddleText>
        <S.MiddleText>{props.stockAmount}</S.MiddleText>
        <Text style={{ fontSize: 25, color: "#D90452" }}>
          {rate < 0 ? rate : `+${rate}`}%
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
        <SmallButton title="매수하기" bgColor="#0046FF" />
      </View>
    </S.Container>
  );
};

export default StockCard;
