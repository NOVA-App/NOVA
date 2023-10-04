import React, { useState, useEffect } from "react";
import { View, Text, ScrollView, Image, Dimensions } from "react-native";
import * as S from "./style";
import SmallButton from "../buttons/SmallButton/index";
import InputSmall from "../input/SmallInput";
import TextInputSmall from "../input/TextInputSmall";
import ToggleButtonSaving from "./ToggleButtonSaving";
import axios from "axios";
import API_URL from "../../../config";
import { useRecoilValue, useRecoilState } from "recoil";
import { gameIdState, refreshState } from "../../recoil/recoil";

const { height, width } = Dimensions.get("window");

const InstallmentSavingsCard = (props) => {
  const [savingsName, setSavingsName] = useState("");
  const [savingsPeriod, setSavingsPeriod] = useState("");
  const [savingsAmount, setSavingsAmount] = useState("");
  const [refresh, setRefresh] = useRecoilState(refreshState);
  const gameID = useRecoilValue(gameIdState);

  const ChangePeriod = (tmp) => {
    setSavingsPeriod(tmp);
  };

  const handleSavings = () => {
    if (savingsName.trim() === "" || savingsAmount.trim() === "") {
      return;
    }

    axios
      .post(API_URL + "/api/saving", {
        gameId: gameID,
        name: savingsName,
        period: savingsPeriod,
        amount: savingsAmount,
      })
      .then((response) => {
        console.log("POST 요청 성공:", response.data);
        setRefresh(!refresh);
      })
      .catch((error) => {
        console.error("POST 요청 오류:", error);
      });
  };

  return (
    <S.Container height={height * 0.4}>
      <S.TagContainer>
        <Text style={{ fontSize: 20, color: "white" }}>적금</Text>
      </S.TagContainer>

      <S.SmallContainer>
        <S.MiddleText>적금 이름</S.MiddleText>
        <TextInputSmall
          height={props.height}
          placeholder="적금 이름을 입력하세요"
          value={savingsName}
          onChangeText={(text) => setSavingsName(text)}
        />
      </S.SmallContainer>

      <S.SmallContainer>
        <S.MiddleText>적금 기간 (년)</S.MiddleText>
        <ToggleButtonSaving
          label="1"
          isSelected={savingsPeriod === 1}
          onPress={() => ChangePeriod(1)}
        />
        <ToggleButtonSaving
          label="2"
          isSelected={savingsPeriod === 2}
          onPress={() => ChangePeriod(2)}
        />
        <ToggleButtonSaving
          label="3"
          isSelected={savingsPeriod === 3}
          onPress={() => ChangePeriod(3)}
        />
      </S.SmallContainer>
      <S.SmallContainer>
        <S.MiddleText>연 납입 금액</S.MiddleText>
        <InputSmall
          height={props.height}
          placeholder="연 납입 금액을 입력하세요"
          value={savingsAmount}
          onChangeText={(txt) => setSavingsAmount(txt)}
        />
      </S.SmallContainer>

      <View
        style={{
          marginTop: "2%",
          flexDirection: "row",
          justifyContent: "flex-end",
        }}
      >
        <SmallButton
          title="가입하기"
          bgColor="#0046FF"
          onPress={handleSavings}
        />
      </View>
    </S.Container>
  );
};

export default InstallmentSavingsCard;
