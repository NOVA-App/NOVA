import React from "react";
import { View, Text, ScrollView, Image, Dimensions } from "react-native";
import * as S from "./style";
import SmallButton from "../../buttons/SmallButton/index";
import axios from "axios";
import API_URL from "../../../../config";
import { refreshState } from "../../../recoil/recoil";
import { useRecoilState } from "recoil";
import { useNavigation } from "@react-navigation/native";

const { height, width } = Dimensions.get("window");

const InstallmentSavingsCard = (props) => {
  const navigation = useNavigation();
  const [refresh, setRefresh] = useRecoilState(refreshState);

  const TerminateHandle = () => {
    console.log(props.id);
    axios
      .delete(`${API_URL}/api/saving/${props.id}`)
      .then((response) => {
        console.log("delete 요청 성공:", response.data);
        alert("해지가 완료 되었습니다");
        navigation.navigate("BankingMainPage");
        setRefresh(!refresh);
      })
      .catch((error) => {
        console.error("delete 요청 오류:", error);
      });
  };

  return (
    <S.Container height={height}>
      <S.SmallContainer>
        <S.MiddleText>적금 이름</S.MiddleText>
        <S.PropsMiddleText>{props.name}</S.PropsMiddleText>
      </S.SmallContainer>

      <S.SmallContainer>
        <S.MiddleText>현재금액</S.MiddleText>
        <S.PropsMiddleText>{[props.totalAmount].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')}</S.PropsMiddleText>
      </S.SmallContainer>

      <S.SmallContainer>
        <S.MiddleText>연 납입 금액</S.MiddleText>
        <S.PropsMiddleText>{[props.amount].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')}</S.PropsMiddleText>
      </S.SmallContainer>

      <S.SmallContainer>
        <S.MiddleText>기간</S.MiddleText>
        <S.PropsMiddleText>
          {props.startAge} ~ {props.endAge}
        </S.PropsMiddleText>
      </S.SmallContainer>

      <View
        style={{
          marginTop: "2%",
          flexDirection: "row",
          justifyContent: "flex-end",
        }}
      >
        <SmallButton
          title="해지하기"
          bgColor="#D90452"
          onPress={TerminateHandle}
        />
      </View>
    </S.Container>
  );
};

export default InstallmentSavingsCard;
