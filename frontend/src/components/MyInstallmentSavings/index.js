import React, { useState, useEffect } from "react";
import { View, Text, ScrollView, Image, Dimensions } from "react-native";
import * as S from "./style";
import SmallButton from "../buttons/SmallButton/index";
import MyInstallmentSavingsCard from "./MyInstallmentSavingsCard/index";
import axios from "axios";
import API_URL from "../../../config";
import { useRecoilValue } from "recoil";
import { gameIdState, refreshState } from "../../recoil/recoil";

const { height, width } = Dimensions.get("window");

const MyInstallmentSavings = (props) => {
  const [savingData, setSavingData] = useState([]);
  const gameID = useRecoilValue(gameIdState);
  const refresh = useRecoilValue(refreshState);

  useEffect(() => {
    axios
      .get(`${API_URL}/api/saving/${gameID}`)
      .then((response) => {
        setSavingData(response.data.data.installmentSavings);
        console.log(response.data.data.installmentSavings);
      })
      .catch((error) => {
        console.error("데이터를 가져오는 동안 오류 발생: ", error);
      });
  }, [refresh]);

  return (
    <S.Container height={height * 0.4}>
      <S.TagContainer>
        <Text style={{ fontSize: 20, color: "white" }}>적금</Text>
      </S.TagContainer>
      <ScrollView style={{ flex: 4 }}>
        {savingData.map((savingItem, index) => (
          <MyInstallmentSavingsCard
            key={index}
            name={savingItem.name}
            totalAmount={savingItem.totalAmount}
            amount={savingItem.amount}
            startAge={savingItem.startAge}
            endAge={savingItem.endAge}
          />
        ))}
      </ScrollView>
    </S.Container>
  );
};

export default MyInstallmentSavings;
