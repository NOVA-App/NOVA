import React, { useState, useEffect } from "react";
import { View, Text, ScrollView } from "react-native";
import StockCard from "./StockCard";
import * as S from "./style";
import axios from "axios";

const StockTrade = () => {
  const [stockData, setStockData] = useState([]);

  useEffect(() => {
    axios
      .get("http://192.168.56.200:8080/api/stock/list/1") // 게임아이디 받아와서 주기
      .then((response) => {
        setStockData(response.data.data);
      })
      .catch((error) => {
        console.error("데이터를 가져오는 동안 오류 발생: ", error);
      });
  }, []);

  return (
    <View style={{ flex: 1, minWidth: "90%" }}>
      <S.Container style={{ flex: 8.5 }}>
        <View
          style={{
            flex: 0.5,
            paddingLeft: "5%",
            justifyContent: "flex-start",
          }}
        >
          <S.TagContainer>
            <Text style={{ fontSize: 20, color: "white" }}>주식 종목</Text>
          </S.TagContainer>
        </View>
        <View style={{ flex: 8 }}>
          <View style={{ marginTop: "5%" }}>
            <ScrollView>
              {stockData.map((stockItem, index) => (
                <StockCard
                  key={index}
                  stockName={stockItem.stockName}
                  stockAmount={stockItem.evaluationAmount}
                  fluctuations={stockItem.fluctuations}
                />
              ))}
            </ScrollView>
          </View>
        </View>
      </S.Container>
    </View>
  );
};

export default StockTrade;
