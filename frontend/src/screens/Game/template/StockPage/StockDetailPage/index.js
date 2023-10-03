import React, { useState, useEffect } from "react";
import { View, Text, TextInput, TouchableOpacity } from "react-native";
import * as S from "./style";
import axios from "axios";
import API_URL from "../../../../../../config";
import { gameIdState } from "../../../../../recoil/recoil";
import { useRecoilState } from "recoil";

const StockDetailPage = (props) => {
  const [buyAmount, setBuyAmount] = useState(0);
  const [adjustAmount, setAdjustAmount] = useState(0);
  const [stockInfo, setStockInfo] = useState({});
  const [gameId] = useRecoilState(gameIdState);
  const rate = props.route.params.rate;
  const stockId = props.route.params.stockId;
  const [refresh, setRefresh] = useState(false);

  useEffect(() => {
    axios
      .get(`${API_URL}/api/stock/${gameId}/${props.route.params.stockId}`)
      .then((response) => {
        console.log(response.data.data)
        setStockInfo(response.data.data);
        setRefresh(!refresh);
      })
      .catch((error) => {
        console.error("데이터를 가져오는 동안 오류 발생: ", error);
      });
  }, [refresh]);

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
    axios
      .post(API_URL + "/api/stock/buy", {
        gameId: gameId,
        stockId: stockId,
        purchaseAmount: buyAmount,
      })
      .then((response) => {
        console.log("POST 요청 성공:", response.data);
        setRefresh(!refresh);
      })
      .catch((error) => {
        console.error("POST 요청 오류:", error);
      });
  };

  const handleSell = () => {
    if (buyAmount > stockInfo.myQuantity) {
      alert("보유 수량보다 많이 판매할 수 없습니다.");
      return;
    }

    axios
      .patch(API_URL + "/api/stock/sell", {
        gameId: gameId,
        stockId: stockId,
        purchaseAmount: buyAmount,
      })
      .then((response) => {
        console.log("POST 요청 성공:", response.data);
        setRefresh(!refresh);
      })
      .catch((error) => {
        console.error("POST 요청 오류:", error);
      });
  };

  if (!stockInfo) {
    return <Text>Loading...</Text>;
  }

  return (
    <View style={{ flex: 1 }}>
      <S.Container style={{ flex: 8.5, minWidth: "80%" }}>
        <View style={{ flex: 8, alignItems: "center" }}>
          <View style={{ marginTop: "5%" }}></View>
          <Text style={{ fontSize: 24, fontWeight: "bold", marginBottom: 10 }}>
            {stockInfo.stockName}
          </Text>
          {/* Line Chart Component */}
          {/* Replace with your line chart component */}
          <View
            style={{
              width: "80%",
              height: 100,
              backgroundColor: "lightgray",
              marginBottom: 20,
            }}
          ></View>
          <Text>{`현재가: ${stockInfo.evaluation}`}</Text>
          <Text>{`상승률: ${rate}%`}</Text>
          <Text>{`내 보유량: ${stockInfo.myQuantity}`}</Text>
          <Text>{`여유자산: 150,000,000원`}</Text>
          <View
            style={{
              flexDirection: "row",
              alignItems: "center",
              marginTop: 10,
            }}
          >
            <View style={{ flex: 1, maxWidth: 120 }}>
              <TextInput
                style={{
                  height: 40,
                  borderColor: "gray",
                  borderWidth: 1,
                  paddingHorizontal: 10,
                  marginRight: 10,
                }}
                onChangeText={handleAdjustAmountChange}
                value={adjustAmount}
                keyboardType="numeric"
                editable={false}
                placeholder={(buyAmount * stockInfo.evaluation).toString()}
              />
            </View>

            <Text style={{ fontSize: 20 }}>원</Text>

            <TouchableOpacity
              onPress={() => handleAdjustQuantity(parseInt(adjustAmount))}
            ></TouchableOpacity>
          </View>
          <View
            style={{
              flexDirection: "row",
              alignItems: "center",
              marginTop: 20,
            }}
          >
            <View style={{ flex: 1, maxWidth: 120 }}>
              <TextInput
                style={{
                  height: 40,
                  borderColor: "gray",
                  borderWidth: 1,
                  paddingHorizontal: 10,
                }}
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
          <View
            style={{
              marginTop: 10,
              marginBottom: 10,
              flexDirection: "row",
              justifyContent: "space-around",
            }}
          >
            <TouchableOpacity
              onPress={handleBuy}
              style={{
                backgroundColor: "#0046FF",
                padding: 10,
                borderRadius: 5,
                width: "40%",
              }}
            >
              <Text style={{ color: "white" }}>매수하기</Text>
            </TouchableOpacity>
            <TouchableOpacity
              onPress={handleSell}
              style={{
                backgroundColor: "#D90452",
                padding: 10,
                borderRadius: 5,
                width: "40%",
              }}
            >
              <Text style={{ color: "white" }}>매도하기</Text>
            </TouchableOpacity>
          </View>
        </View>
      </S.Container>
    </View>
  );
};

export default StockDetailPage;
