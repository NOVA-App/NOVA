import React, { useState, useEffect } from "react";
import { View, Text, ScrollView, Image, Dimensions } from "react-native";
import * as S from "./style";
import SmallButton from "../buttons/SmallButton/index";
import axios from "axios";
import API_URL from "../../../config";
import { useRecoilValue } from "recoil";
import { gameIdState, refreshState } from "../../recoil/recoil";

const MyIRP = () => {
  const { height, width } = Dimensions.get("window");
  const [costData, setCostData] = useState(0);
  const gameID = useRecoilValue(gameIdState);
  const refresh = useRecoilValue(refreshState);
  useEffect(() => {
    axios
      .get(`${API_URL}/api/saving/${gameID}`)
      .then((response) => {
        console.log(response.data.data.irpCost);
        setCostData(response.data.data.irpCost);
      })
      .catch((error) => {
        console.error(error);
      });
  }, [refresh]);

  return (
    <S.Container height={height * 0.4}>
      <S.TagContainer>
        <Text style={{ fontSize: 20, color: "white" }}>IRP</Text>
      </S.TagContainer>

      <S.SmallContainer>
        <S.MiddleText>현재금액</S.MiddleText>
        <Text>{[costData].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')}원</Text>
      </S.SmallContainer>

      <View
        style={{
          marginTop: "2%",
          flexDirection: "row",
          justifyContent: "flex-end",
        }}
      ></View>
    </S.Container>
  );
};

export default MyIRP;
