import React, { useState } from "react";
import { View, Text, ScrollView, Image, Dimensions } from "react-native";
import * as S from "./style";
import SmallButton from "../buttons/SmallButton/index";
import InputSmall from "../input/SmallInput";
import axios from "axios"; // axios 라이브러리 추가
import API_URL from "../../../config";

const { height, width } = Dimensions.get("window");

const IRPCard = (props) => {
  const [irpCost, setIrpCost] = useState("");

  const handleIrpPayment = () => {
    if (irpCost.trim() === "") {  //입력값이 비었을 떄
      return;
    }

      axios
        .put(API_URL + "/api/saving", {
          'gameId': 1, 
          'irpCost': irpCost, 
        })
        .then((response) => {
          console.log("PUT 요청 성공11:", response.data);
          console.log("PUT 요청 성공22:", irpCost);
        })
        .catch((error) => {
          console.error("PUT 요청 오류:", error);
        });
  };

  return (
    <S.Container height={height * 0.4}>
      <S.TagContainer>
        <Text style={{ fontSize: 20, color: "white" }}>IRP</Text>
      </S.TagContainer>

      <S.SmallContainer>
        <S.MiddleText>납입 금액</S.MiddleText>
        <InputSmall
          height={props.height}
          placeholder="금액을 입력하세요"
          value={irpCost}
          onChangeText={(text) => setIrpCost(text)}
          onSubmitEditing={handleIrpPayment}
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
          title="납입하기"
          bgColor="#0046FF"
          onPress={handleIrpPayment}
        />
      </View>
    </S.Container>
  );
};

export default IRPCard;
