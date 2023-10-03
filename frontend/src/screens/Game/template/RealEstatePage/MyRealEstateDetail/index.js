import React, { useState, useEffect } from "react";
import { Dimensions, View, Text, ScrollView } from "react-native";
import * as S from "./style";
import HouseImg from "../../../../../assets/House.png";
import Button from "../../../../../components/buttons/SmallButton";
import axios from "axios";
import API_URL from "../../../../../../config";
import { useRecoilValue } from "recoil";
import { gameIdState } from "../../../../../recoil/recoil";

const { height } = Dimensions.get("window");

const MyRealEstateDetail = (props) => {
  const ID = props.route.params.realtyId
  const [realtyData, setRealtyData] = useState([]);
  const gameID = useRecoilValue(gameIdState)

  useEffect(() => {
    axios
      .get(`${API_URL}/api/realty/mine/${gameID}/${ID}`) // 부동산 아이디 받아와서 주기
      .then((response) => {
        setRealtyData(response.data.data);
      })
      .catch((error) => {
        console.error("데이터를 가져오는 동안 오류 발생: ", error);
      });
  }, []);

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
            {realtyData.realtyName} ({"  "}
            <S.InfoText style={{ color: "#D90452" }}>{(realtyData.evaluationAmount - realtyData.investAmount)/realtyData.investAmount * 100}</S.InfoText> )
          </S.InfoText>
          <S.InfoText>{`투자 금액:    `}{realtyData.investAmount}</S.InfoText>
          <S.InfoText>{`현재가:         `}{realtyData.evaluationAmount}</S.InfoText>
          <S.InfoText>
            {`투자 수익률:          `}
            <S.InfoText style={{ color: "#D90452" }}>{(realtyData.evaluationAmount - realtyData.investAmount)/realtyData.investAmount * 100} % </S.InfoText>
          </S.InfoText>
          <S.InfoText>{`월세 수익:        `}{realtyData.rentIncome}</S.InfoText>
          <S.InfoText>{`남은 대출금:     `}{realtyData.principal}</S.InfoText>
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
