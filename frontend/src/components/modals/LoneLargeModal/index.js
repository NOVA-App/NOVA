import { Modal, Dimensions, Text, StyleSheet } from 'react-native';
import React, { useState, useEffect } from "react";
import styled from 'styled-components/native';
import * as S from './style'
import Button from '../../buttons/MediumButton/index'
import InputSmall from '../../input/SmallInput';
import { gameIdState } from '../../../recoil/recoil';
import { useRecoilValue } from "recoil";
import axios from "axios";
import API_URL from '../../../../config';

const LoneLargeModal = (props) => {

  const gameID = useRecoilValue(gameIdState)
  const [loanCost, setLoanCost] = useState(0);

  const styles = StyleSheet.create({
    Text:{
      fontSize: 40,
      Colors: 'black',
    }
  })

  useEffect(() => {
    console.log('여유자금', props.usableAsset)
    console.log('loan', loanCost)
  },[])

  const handlePurchase = () => {

  }
 
  const handleLoan = () => {
    if (parseFloat(loanCost) + props.usableAsset >= props.totalPrice) {
      axios
        .post(API_URL + "/api/realty/buy", {
          gameId: gameID,
          realtyId: props.realtyId,
          principalAmount: parseFloat(loanCost)
        })
        .then((response) => {
          console.log("POST 요청 성공:", response.data);
        })
        .catch((error) => {
          console.error("POST 요청 오류:", error);
        });
    } else {
      alert("자금이 부족합니다.");
    }
  }

  return (
    // <Modal animationType="fade"  // 스타일 둘 중 하나 택 1
    <Modal animationType="slide" 
      transparent={true} 
      visible={props.isVisible}
      >
    <S.ModalContent>
      <S.TitleContainer>
        <Text 
          style={styles.Text} 
          onPress={props.onClose}
          > X</Text>
      </S.TitleContainer>
      <Text style={{ fontSize: 18 }}>{props.title}</Text>
      <InputSmall
        placeholder="대출 신청금액을 입력하세요"
        value={loanCost}
        onChangeText={(text) => setLoanCost(text)}
      />
      <Text style={{ fontSize: 18 }}>연간 이자:  {loanCost * 0.07}</Text>
      <S.ButtonContainer>
        <Button 
          title={props.btnTitle}
          onPress={handleLoan}
          />
      </S.ButtonContainer>
    </S.ModalContent>
  </Modal>
  )
}



//   const styledTitleText = styled.Text`
//   color: '#fff';
//   font-size: 30;
// `

export default LoneLargeModal;