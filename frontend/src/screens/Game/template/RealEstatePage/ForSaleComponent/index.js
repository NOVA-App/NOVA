import React, { useState, useEffect } from "react";
import { Dimensions, View, Text, ScrollView } from "react-native";
import * as S from "./style";
import HouseImg from "../../../../../assets/House.png";
import Button from "../../../../../components/buttons/SmallButton";
import axios from "axios";
import API_URL from "../../../../../../config";
import { useRecoilValue } from "recoil";
import { tokenState, gameIdState, gameDataState } from "../../../../../recoil/recoil";
import LoneLargeModal from "../../../../../components/modals/LoneLargeModal";

// 매물목록 상세 페이지
const ForSaleDetail = (props) => {
  const ID = props.route.params.realtyId
  const { height } = Dimensions.get("window");
  const [realtyData, setRealtyData] = useState([]);
  const token = useRecoilValue(tokenState);
  const gameID = useRecoilValue(gameIdState)
  const gameData = useRecoilValue(gameDataState)
  const usableAsset = gameData.annualAssets.usableAsset
  const [isModalVisible, setIsModalVisible] = useState(false);
  const onModalClose = () => {
    setIsModalVisible(false);
  };


  useEffect(() => {
    // 부동산 매물 디테일 받아오기
      axios
      .get(`${API_URL}/api/realty/${gameID}/${ID}`) // 부동산 아이디 받아와서 주기
      .then((response) => {
        setRealtyData(response.data.data);
        console.log(realtyData)
        console.log(gameData)
      })
      .catch((error) => {
        console.error("부동산 매물 상세 에러: ", error);
      });
  }, []);

 
  return (
    <View style={{ flex: 1 }}>
      <LoneLargeModal
        realtyId = {ID}
        enableLoanAmount = {realtyData.enableLoanAmount}
        usableAsset = {usableAsset}
        totalPrice = {realtyData.totalPrice}
        isVisible={isModalVisible}
        onClose={onModalClose}
        btnTitle='매수'
        title= {`총 금액: ${realtyData.totalPrice}\n 
가능 대출 금액:  ${realtyData.enableLoanAmount}\n
모자란 금액:  ${usableAsset - realtyData.totalPrice < 0 ? usableAsset - realtyData.totalPrice : 0}\n
여유 자금:  ${usableAsset}\n
대출 신청금액: `} >
    </LoneLargeModal>
      <S.Container style={{ flex: 8.5, minWidth: "80%" }}>
        <View
          style={{
            flex: 0.2,
          }}
        ></View>
        <View style={{ flex: 8, alignItems: "center" }}>
        <View>
          <S.ImgContainer source={{uri: realtyData.realtyImg}}></S.ImgContainer>
          <View style={{ marginTop: "5%" }}></View>
          <S.InfoText>
          {realtyData.realtyName} ({"  "}
            <S.InfoText style={{ color: "#D90452" }}>+20%</S.InfoText> )
          </S.InfoText>
          <S.InfoText>{`지역    `}{realtyData.region}</S.InfoText>
          <S.InfoText>{`예상 월세 수익    `}{realtyData.predictedRentIncome}</S.InfoText>
          <S.InfoText>
            {`총금액          `}
            {realtyData.totalPrice}
          </S.InfoText>
          <S.InfoText>
            {`매물가격          `}
            {realtyData.evaluationAmount}
          </S.InfoText>
          <S.InfoText>{`취득세        `}{realtyData.acquistionTax}</S.InfoText>
          <S.InfoText>
            {`모자란 금액     `}
            <S.InfoText style={{ color: "#D90452" }}>{usableAsset - realtyData.totalPrice < 0 ? usableAsset - realtyData.totalPrice : 0}</S.InfoText>
          </S.InfoText>
        </View>


        </View>
        <View
          style={{
            marginBottom: "5%",
            marginRight: "5%",
            alignItems: "flex-end",
          }}
        >
          <Button title="매수하기" bgColor="#0046FF" 
            onPress={() => {
            setIsModalVisible(true);
            }}

          />
        </View>
      </S.Container>
    </View>
  );
};

export default ForSaleDetail;
