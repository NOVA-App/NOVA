import React, { useState, useEffect } from "react";
import { Dimensions, View, Text, ScrollView } from "react-native";
import * as S from "./style";
import HouseImg from "../../../../../assets/House.png";
import Button from "../../../../../components/buttons/SmallButton";
import axios from "axios";
import API_URL from "../../../../../../config";
import { useRecoilValue, useRecoilState } from "recoil";
import {
  gameIdState,
  gameDataState,
  refreshState,
} from "../../../../../recoil/recoil";

const { height } = Dimensions.get("window");

const MyRealEstateDetail = (props) => {
  const ID = props.route.params.realtyId;
  const [realtyData, setRealtyData] = useState([]);
  const gameID = useRecoilValue(gameIdState);
  const gameData = useRecoilValue(gameDataState);
  const usableAsset = gameData.annualAssets.usableAsset;
  const [refresh, setRefresh] = useRecoilState(refreshState);

  const SellHandle = () => {
    if (parseFloat(realtyData.evaluationAmount) + usableAsset < realtyData.principal) {
      alert("대출 상환 자금이 부족합니다.");
    } else {
      // 여유자금 바꿔주기
      axios
        .delete(`${API_URL}/api/realty/sell/${gameID}/${ID}`)
        .then((response) => {
          console.log("delete 요청 성공:", response.data);
          setRefresh(!refresh);
        })
        .catch((error) => {
          console.error("delete 요청 오류:", error);
        });
    }
  };

  useEffect(() => {
    axios
      .get(`${API_URL}/api/realty/mine/${gameID}/${ID}`) // 부동산 아이디 받아와서 주기
      .then((response) => {
        setRealtyData(response.data.data);
      })
      .catch((error) => {
        console.error("데이터를 가져오는 동안 오류 발생: ", error);
      });
  }, [refresh]);

  return (
    <View style={{ flex: 1 }}>
      <S.Container style={{ flex: 8.5, minWidth: "80%" }}>
        <View
          style={{
            flex: 0.2,
          }}
        ></View>
        <View style={{ flex: 8, alignItems: "center" }}>
          <S.ImgContainer source={{ uri: realtyData.realtyImg }}></S.ImgContainer>
          <View style={{ marginTop: "5%" }}></View>
          <S.NameText>
            {realtyData.realtyName} {"  \n"}
          </S.NameText>
          <S.InfoText>
            {`투자 금액:    `}
            {realtyData.investAmount}
          </S.InfoText>
          <S.InfoText>
            {`현재가:         `}
            {realtyData.evaluationAmount}
          </S.InfoText>
          <S.InfoText>
            {`투자 수익률:          `}
            <S.InfoText style={{ color: "#D90452" }}>
              {(
                ((realtyData.evaluationAmount - realtyData.investAmount) /
                  realtyData.investAmount) *
                100
              ).toFixed(2)}{" "}
              %{" "}
            </S.InfoText>
          </S.InfoText>
          <S.InfoText>
            {`월세 수익:        `}
            {realtyData.rentIncome}
          </S.InfoText>
          <S.InfoText>
            {`남은 대출금:     `}
            {realtyData.principal}
          </S.InfoText>
        </View>
        <View
          style={{
            marginBottom: "5%",
            marginRight: "5%",
            alignItems: "flex-end",
          }}
        >
          <Button title="매도하기" bgColor="#D90452" onPress={SellHandle} />
        </View>
      </S.Container>
    </View>
  );
};

export default MyRealEstateDetail;
