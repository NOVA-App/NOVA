import React, { useState, useEffect } from "react";
import { Dimensions, View, Text, TextInput, TouchableOpacity } from "react-native";
import * as S from './style';
import axios from 'axios';
import API_URL from "../../../../../../config";
import { gameDataState } from "../../../../../recoil/recoil"
import { useRecoilState, useRecoilValue } from "recoil";

const { height, width } = Dimensions.get("window");

const StockDetailPage = ({ stockId }) => {
  const [buyAmount, setBuyAmount] = useState("0");
  const [adjustAmount, setAdjustAmount] = useState("0");
  const [stockInfo, setStockInfo] = useState(null);
  const gameData = useRecoilValue(gameDataState);
  const gameId = gameData.gameId
  useEffect(() => {
    // Replace the URL with your actual API endpoint
        // Mock gameId and stockId, replace them with the actual values you want to use

    // Fetch stock information
    const fetchData = async () => {
      console.log(gameData.gameId)
      console.log(stockId)
      try {
        const response = await axios.get(`${API_URL}/api/stock/${gameData.gameId}/${stockId}`);
        console.log(response.data)
        setStockInfo(response.data.stockInfo);
        console.log(stockInfo)
      } catch (error) {
        console.error('API 호출 오류:', error);
      }
    };

    fetchData();
  }, [gameData.gameId, stockId]); // Empty dependency array means this effect runs once when the component mounts

  const handleBuyAmountChange = (text) => {
    setBuyAmount(text);
  };

  const handleAdjustAmountChange = (text) => {
    setAdjustAmount(text);
  };

  const handleAdjustQuantity = (amount) => {
    const newAmount = parseInt(buyAmount) + amount;
    setBuyAmount(newAmount >= 0 ? newAmount.toString() : "0");
  };

  const handleBuy = () => {
    console.log("Buy:", buyAmount);
    // Add your logic for buying here
    // You may want to make an API call to execute the buy action on the server
  };

  const handleSell = () => {
    console.log("Sell:", buyAmount);
    // Add your logic for selling here
    // You may want to make an API call to execute the sell action on the server
  };

  if (!stockInfo) {
    return <Text>Loading...</Text>;
  }

  return (
    <View style={{ flex: 1 }}>
      <S.Container style={{ flex: 8.5, minWidth: "80%" }}>
        <View style={{ flex: 8, alignItems: "center" }}>
          <View style={{ marginTop: "5%" }}></View>
          <Text style={{ fontSize: 24, fontWeight: "bold", marginBottom: 10 }}>{stockInfo.stockName}</Text>
          {/* Line Chart Component */}
          {/* Replace with your line chart component */}
          <View style={{ width: "80%", height: 100, backgroundColor: "lightgray", marginBottom: 20 }}></View>
          <Text>{`현재가: ${stockInfo.evaluation}`}</Text>
          <Text>{`상승률: ${stockInfo.fluctuations}%`}</Text>
          <Text>{`내 보유량: ${stockInfo.myQuantity}`}</Text>
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
