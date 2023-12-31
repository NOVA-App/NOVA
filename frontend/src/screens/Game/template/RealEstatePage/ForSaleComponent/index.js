import React, { useState, useEffect } from "react";
import { Dimensions, View, Text, ScrollView } from "react-native";
import * as S from "./style";
import HouseImg from "../../../../../assets/House.png";
import Button from "../../../../../components/buttons/SmallButton";
import axios from "axios";
import API_URL from "../../../../../../config";
import { useRecoilValue } from "recoil";
import {
  accessTokenState,
  gameIdState,
  gameDataState,
} from "../../../../../recoil/recoil";
import LoanLargeModal from "../../../../../components/modals/LoanLargeModal";

// 매물목록 상세 페이지
const ForSaleDetail = (props) => {
  const ID = props.route.params.realtyId;
  const { height } = Dimensions.get("window");
  const [realtyData, setRealtyData] = useState([]);
  const token = useRecoilValue(accessTokenState);
  const gameID = useRecoilValue(gameIdState);
  const gameData = useRecoilValue(gameDataState);
  const usableAsset = gameData.annualAssets.usableAsset;
  const [isModalVisible, setIsModalVisible] = useState(false);

  const onModalClose = () => {
    setIsModalVisible(false);
  };
  console.log(realtyData.enableLoanAmount);
  useEffect(() => {
    // 부동산 매물 디테일 받아오기
    axios
      .get(`${API_URL}/api/realty/${gameID}/${ID}`) // 부동산 아이디 받아와서 주기
      .then((response) => {
        setRealtyData(response.data.data);
        console.log(realtyData);
        console.log(gameData);
      })
      .catch((error) => {
        console.error("부동산 매물 상세 에러: ", error);
      });
      // console.log(props)
  }, []);

  return (
    <View style={{ flex: 1 }}>
      <LoanLargeModal
        realtyId={ID}
        enableLoanAmount={realtyData.enableLoanAmount}
        usableAsset={usableAsset}
        totalPrice={realtyData.totalPrice}
        isVisible={isModalVisible}
        onClose={onModalClose}
        btnTitle="매수"
        title={`총 금액: ${[realtyData.totalPrice].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')}\n 
가능 대출 금액:  ${[realtyData.enableLoanAmount].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')}\n
모자란 금액:  ${
          usableAsset - realtyData.totalPrice < 0
            ? [usableAsset - realtyData.totalPrice].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')
            : 0
        }\n
여유 자금:  ${[usableAsset].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')}\n
대출 신청금액: `}
        maxAmount={realtyData.enableLoanAmount}
      ></LoanLargeModal>
      <S.Container style={{ flex: 8.5, minWidth: "80%" }}>
        <View
          style={{
            flex: 0.2,
          }}
        ></View>
        <View style={{ flex: 8, alignItems: "center" }}>
          <View>
            <S.ImgContainer
              source={{ uri: realtyData.realtyImg }}
            ></S.ImgContainer>
            <View style={{ marginTop: "5%" }}></View>
            <S.InfoText>
              {realtyData.realtyName} ({"  "}
              <S.InfoText style={{ color: "#D90452" }}>+20%</S.InfoText> )
            </S.InfoText>
            <S.InfoText>
              {`지역    `}
              {realtyData.region}
            </S.InfoText>
            <S.InfoText>
              {`예상 월세 수익    `}
              {[realtyData.predictedRentIncome].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')}
            </S.InfoText>
            <S.InfoText>
              {`총금액          `}
              {[realtyData.totalPrice].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')}
            </S.InfoText>
            <S.InfoText>
              {`매물가격          `}
              {[realtyData.evaluationAmount].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')}
            </S.InfoText>
            <S.InfoText>
              {`취득세        `}
              {[realtyData.acquistionTax].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')}
            </S.InfoText>
            <S.InfoText>
              {`모자란 금액     `}
              <S.InfoText style={{ color: "#D90452" }}>
                {usableAsset - realtyData.totalPrice < 0
                  ? [usableAsset - realtyData.totalPrice].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')
                  : 0}
              </S.InfoText>
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
          <Button
            title="매수하기"
            bgColor="#0046FF"
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
