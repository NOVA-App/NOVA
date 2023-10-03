import React, { useState, useEffect } from "react";
import { View } from "react-native";
import * as S from "./style";
import HouseImg from "../../../../assets/House.png";
import SmallButton from "../../../buttons/SmallButton";
import LoanSmallModal from "../../../modals/LoanSmallModal";
import API_URL from "../../../../../config";
import axios from "axios";



const HouseCard = (props) => {
  const [isModalVisible, setIsModalVisible] = useState(false);
  
  const [principalAmount, setPrincipalAmount] = useState(props.principal);
  
  const onModalClose = () => {
    setIsModalVisible(false);
  };

  const onModalOpen = () => {
    setIsModalVisible(true);
  };

  const handleRepayment = () => {
    console.log('props.gameID',props.gameID)
    console.log('props.realtyId',props.realtyId)
    console.log('principalAmount',principalAmount)
    axios
    .patch(API_URL + "/api/realty/loan/repayment", {
      gameId: props.gameID,
      realtyId: props.realtyId,
      principalAmount: principalAmount,
    })
    .then((response) => {
      console.log("PATCH 요청 성공!!:", response.data);
    })
    .catch((error) => {
      console.error("PATCH 요청 오류 ㅠㅅㅠ:", error);
    });
    setIsModalVisible(false);
  }


  return (
    <S.Container height={props.height}>
      <S.ImgBox source={HouseImg} />
      <S.ContentContainer>
        <S.TextContainer>
          <S.MiddleText>{props.realtyName}</S.MiddleText>
        </S.TextContainer>
        <S.TextContainer>
          <S.MiddleText>{`금액`}</S.MiddleText>
          <S.MiddleText>{props.realtyPrice}</S.MiddleText>
        </S.TextContainer>
        <S.TextContainer>
          <S.MiddleText>{`남은 상환금`}</S.MiddleText>
          <S.MiddleText>{props.principal}</S.MiddleText>
        </S.TextContainer>
        <View
          style={{
            width: "100%",
            marginTop: "5%",
            flexDirection: "row",
            justifyContent: "flex-end",
          }}
        >
          <SmallButton 
            title="상환하기" 
            bgColor="#0046FF" 
            onPress={onModalOpen}
          />
          <LoanSmallModal
            animationType="slide"
            transparent={true}
            visible={isModalVisible}
            title='상환하기'
            onClose={onModalClose}
      />
          
        </View>
      </S.ContentContainer>
    </S.Container>
  );
};

export default HouseCard;
