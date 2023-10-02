import React, { useState } from "react";
import { Dimensions, View, Text, TextInput, TouchableOpacity } from "react-native";
import * as S from './style';

const { height, width } = Dimensions.get("window");

const StockDetailPage = () => {
  const [buyAmount, setBuyAmount] = useState("0");
  const [adjustAmount, setAdjustAmount] = useState("0");

  const handleBuyAmountChange = (text) => {
    // Handle changes in the buy amount input
    setBuyAmount(text);
  };

  const handleAdjustAmountChange = (text) => {
    // Handle changes in the adjust amount input
    setAdjustAmount(text);
  };

  const handleAdjustQuantity = (amount) => {
    // Handle adjusting quantity
    const newAmount = parseInt(buyAmount) + amount;
    setBuyAmount(newAmount >= 0 ? newAmount.toString() : "0");
  };

  const handleBuy = () => {
    // Handle the buy action
    console.log("Buy:", buyAmount);
    // Add your logic for buying here
  };

  const handleSell = () => {
    // Handle the sell action
    console.log("Sell:", buyAmount);
    // Add your logic for selling here
  };

  return (
    <View style={{ flex: 1 }}>
      <S.Container style={{ flex: 8.5, minWidth: "80%" }}>
        <View style={{ flex: 8, alignItems: "center" }}>
          <View style={{ marginTop: "5%" }}></View>
          <Text style={{ fontSize: 24, fontWeight: "bold", marginBottom: 10 }}>00전자</Text>
          {/* Line Chart Component */}
          {/* Replace with your line chart component */}
          <View style={{ width: "80%", height: 100, backgroundColor: "lightgray", marginBottom: 20 }}></View>
          <Text>{`현재가: 5,000,000`}</Text>
          <Text>{`상승률: -30%`}</Text>
          <Text>{`내 보유량: 50`}</Text>
          <Text>{`여유자산: 150,000,000원`}</Text>
          <View style={{ flexDirection: "row", alignItems: "center", marginTop: 10 }}>
            <View style={{ flex: 1, maxWidth: 120 }}>
              <TextInput
                style={{ height: 40, borderColor: "gray", borderWidth: 1, paddingHorizontal: 10, marginRight: 10 }}
                onChangeText={handleAdjustAmountChange}
                value={adjustAmount}
                keyboardType="numeric"
                placeholder="수량 조정"
              />
            </View>
            <TouchableOpacity onPress={() => handleAdjustQuantity(parseInt(adjustAmount))}>
              <Text style={{ fontSize: 20 }}>수량</Text>
            </TouchableOpacity>
          </View>
          <View style={{ flexDirection: "row", alignItems: "center", marginTop: 20 }}>
            <View style={{ flex: 1, maxWidth: 120 }}>
              <TextInput
                style={{ height: 40, borderColor: "gray", borderWidth: 1, paddingHorizontal: 10 }}
                onChangeText={handleBuyAmountChange}
                value={buyAmount}
                keyboardType="numeric"
                placeholder="수량 입력"
              />
            </View>
            <View style={{ flexDirection: "row", marginLeft: 10 }}>
              <TouchableOpacity onPress={() => handleAdjustQuantity(1)}>
                <Text style={{ fontSize: 20, marginRight: 10 }}>+</Text>
              </TouchableOpacity>
              <TouchableOpacity onPress={() => handleAdjustQuantity(-1)}>
                <Text style={{ fontSize: 20 }}>-</Text>
              </TouchableOpacity>
            </View>
          </View>
          <View style={{ marginTop: 10, marginBottom: 10, flexDirection: "row", justifyContent: "space-around" }}>
            <TouchableOpacity onPress={handleBuy} style={{ backgroundColor: "#0046FF", padding: 10, borderRadius: 5, width: "40%" }}>
              <Text style={{ color: "white" }}>매수하기</Text>
            </TouchableOpacity>
            <TouchableOpacity onPress={handleSell} style={{ backgroundColor: "#D90452", padding: 10, borderRadius: 5, width: "40%" }}>
              <Text style={{ color: "white" }}>매도하기</Text>
            </TouchableOpacity>
          </View>
        </View>
      </S.Container>
    </View>
  );
};

export default StockDetailPage;
