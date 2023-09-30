import React, { useState, useEffect } from "react";
import { Dimensions, View, Text, ScrollView } from "react-native";
import HouseCard from "./HouseCard";
import * as S from "./style";
import axios from "axios";

const { height } = Dimensions.get("window");

const MyRealEstate = () => {
  const [myRealtyData, setMyRealtyData] = useState([]);
  const [myHouseData, setMyHouseData] = useState([]);
  useEffect(() => {
    axios
      .get("http://192.168.56.200:8080/api/realty/mine/1") // 게임아이디 받아와서 주기
      .then((response) => {
        setMyRealtyData(response.data.data);
        setMyHouseData(response.data.data.myRealties);
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
            <Text style={{ fontSize: 20, color: "white" }}>내 부동산 현황</Text>
          </S.TagContainer>
        </View>
        <View style={{ flex: 8 }}>
          <View>
            <S.TotalAssetContainer>
              <S.TextContainer>
                <S.MiddleText>{`총 투자금`}</S.MiddleText>
                <S.MiddleText>{myRealtyData.investAmounts}</S.MiddleText>
              </S.TextContainer>
              <S.TextContainer>
                <S.MiddleText>{`총 평가금`}</S.MiddleText>
                <S.MiddleText>{myRealtyData.evaluationAmounts}</S.MiddleText>
              </S.TextContainer>
              <S.TextContainer>
                <S.MiddleText>{`월세 수익`}</S.MiddleText>
                <S.MiddleText>{myRealtyData.totalRentalIncome}</S.MiddleText>
              </S.TextContainer>
            </S.TotalAssetContainer>
          </View>
          <View style={{ marginTop: "5%" }}>
            <ScrollView>
              {myHouseData.map((realtyItem, index) => (
                <HouseCard
                  key={index}
                  realtyId={realtyItem.realtyId}
                  realtyName={realtyItem.realtyName}
                  investAmount={realtyItem.investAmount}
                  evaluationAmount={realtyItem.evaluationAmount}
                  rentalIncome={realtyItem.rentalIncome}
                />
              ))}
              <HouseCard height={height} />
            </ScrollView>
          </View>
        </View>
      </S.Container>
    </View>
  );
};

export default MyRealEstate;
