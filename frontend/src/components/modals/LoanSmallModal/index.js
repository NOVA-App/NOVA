import { Modal, Dimensions, Text, StyleSheet } from "react-native";
import styled from "styled-components/native";
import * as S from "./style";
import Button from "../../buttons/MediumButton/index";
import InputSmall from "../../input/SmallInput";
import React, { useState, useEffect } from "react";
import axios from "axios";
import API_URL from "../../../../config";
import { refreshState } from "../../../recoil/recoil";
import { useRecoilState } from "recoil";

const LoanSmallModal = (props) => {
  const maxAmount = props.maxAmount;
  const minAmount = props.minAmount;
  const [loanPayment, setLoanPayment] = useState(0);
  const [refresh, setRefresh] = useRecoilState(refreshState);

  const styles = StyleSheet.create({
    Text: {
      fontSize: 40,
      color: "black",
    },
  });

  const handleRepayment = () => {
    axios
      .patch(API_URL + "/api/realty/loan/repayment", {
        gameId: props.gameID,
        realtyId: props.realtyId,
        principalAmount: parseFloat(loanPayment),
      })
      .then((response) => {
        console.log("PATCH 요청 성공!!:", response.data);
        setRefresh(!refresh);
      })
      .catch((error) => {
        console.error("PATCH 요청 오류 ㅠㅅㅠ:", error);
      });
  };

  return (
    // <Modal animationType="fade"  // 스타일 둘 중 하나 택 1
    <Modal animationType="slide" transparent={true} visible={props.visible}>
      <S.ModalContent>
        <S.TitleContainer>
          <Text style={styles.Text} onPress={props.onClose}>
            {" "}
            X
          </Text>
        </S.TitleContainer>
        <Text style={{ fontSize: 14 }}>{props.title}</Text>
        <InputSmall
          placeholder="상환금액을 입력하세요"
          value={loanPayment}
          onChangeText={(text) => {
            if (parseFloat(text) > maxAmount || parseFloat(text) > minAmount) {
              alert("대출 상환금액이 최대 허용 금액을 초과했습니다.");
            } else {
              setLoanPayment(text);
            }
          }}
        />
        <S.ButtonContainer>
          <Button
            title="상환하기"
            bgColor="#6742C5"
            onPress={() => {
              handleRepayment();
              props.onClose();
            }}
          />
        </S.ButtonContainer>
      </S.ModalContent>
    </Modal>
  );
};

export default LoanSmallModal;
