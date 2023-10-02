import React, { useState } from "react";
import { Dimensions, View, Text, TextInput, Button as RNButton } from "react-native";
import * as S from "./style";
import HouseImg from "../../../../assets/House.png";
import Button from "../../../buttons/SmallButton";

const { height, width } = Dimensions.get("window");

const RealEstateDetail = (props) => {
  const [buyAmount, setBuyAmount] = useState("0");

  const handleBuyAmountChange = (text) => {
    setBuyAmount(text);
  };

  const handleBuy = () => {
    console.log("Buy:", buyAmount);
  };

  const handleSell = () => {
    console.log("Sell:", buyAmount);
  };

  return (
    <View style={{ flex: 1 }}>
      <S.Container style={{ flex: 8.5, minWidth: "80%" }}>
        <View style={{ flex: 0.2 }}></View>
        <View style={{ flex: 8, alignItems: "center" }}>
          <S.ImgContainer source={HouseImg}></S.ImgContainer>
          <View style={{ marginTop: "5%" }}></View>
          <Text style={{ fontSize: 24, fontWeight: "bold", marginBottom: 10 }}>00전자</Text>
          <View style={{ width: "80%", height: 100, backgroundColor: "lightgray", marginBottom: 20 }}></View>
          <Text>{`현재가: 5,000,000`}</Text>
          <Text>{`상승률: -30%`}</Text>
          <Text>{`내 보유량: 50`}</Text>
          <Text>{`여유자산: 150,000,000원`}</Text>
          <View style={{ flexDirection: "row", alignItems: "center", marginTop: 20 }}>
            <TextInput
              style={{ height: 40, borderColor: "gray", borderWidth: 1, marginRight: 10, paddingHorizontal: 10 }}
              onChangeText={handleBuyAmountChange}
              value={buyAmount}
              keyboardType="numeric"
            />
            <RNButton title="입력" onPress={() => console.log("Adjust quantity")} />
          </View>
        </View>
        <View style={{ flexDirection: "row", justifyContent: "space-around", marginBottom: 10 }}>
          <Button title="매수하기" bgColor="#0046FF" onPress={handleBuy} />
          <Button title="매도하기" bgColor="#D90452" onPress={handleSell} />
        </View>
      </S.Container>
    </View>
  );
};

export default RealEstateDetail;
