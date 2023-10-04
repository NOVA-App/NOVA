import React, { useState } from "react";
import {
  View,
  Text,
  ScrollView,
  Image,
  Dimensions,
  TouchableOpacity,
} from "react-native";
import * as S from "./style";
import SmallButton from "../buttons/SmallButton/index";
import InputSmall from "../input/SmallInput";
import axios from "axios"; // axios 라이브러리 추가
import API_URL from "../../../config";
import { useRecoilValue, useRecoilState } from "recoil";
import { gameIdState, refreshState } from "../../recoil/recoil";
import SmallModal from "../modals/SmallModal";
import { useNavigation } from "@react-navigation/native";

const { height, width } = Dimensions.get("window");

const IRPCard = (props) => {
  const [irpCost, setIrpCost] = useState("");
  const gameID = useRecoilValue(gameIdState);
  const [refresh, setRefresh] = useRecoilState(refreshState);
  const [isModalVisible, setIsModalVisible] = useState(false);
  const navigation = useNavigation();

  const onModalClose = () => {
    setIsModalVisible(false);
  };
  const handleIrpPayment = () => {
    if (irpCost.trim() === "") {
      //입력값이 비었을 떄
      return;
    }
    axios
      .put(API_URL + "/api/saving", {
        gameId: gameID,
        irpCost: irpCost,
      })
      .then((response) => {
        console.log("PUT 요청 성공11:", response.data);
        console.log("PUT 요청 성공22:", irpCost);
        alert("납입이 완료 되었습니다");
        navigation.navigate("BankingMainPage");
        setRefresh(!refresh);
      })
      .catch((error) => {
        console.error("PUT 요청 오류:", error);
      });
  };

  return (
    <S.Container height={height * 0.4}>
      <SmallModal
        isVisible={isModalVisible}
        onClose={onModalClose}
        title={`      개인 퇴직 연금

      근로자가 퇴직 또는 이직 시 받는 
      퇴직금 일시금을 수령하는 계좌입니다.

      납입만 해도 세액 공제 혜택을 받을 수 있습니다. 

      하지만 55세 이전 중도중도 해지할 경우, 
      세제 혜택을 모두 반환해야 합니다.

      55세 이후
      연금 보험에 가입하여 수령할 수도 있고, 
      기간과 비율을 지정하여 수령할 수도 있습니다.
      `}
      />
      <S.TagContainer>
        <Text style={{ fontSize: 20, color: "white" }}>IRP</Text>
      </S.TagContainer>
      <S.InfoContainer>
        <TouchableOpacity
          onPress={() => {
            setIsModalVisible(true);
          }}
        >
          <Text style={{ fontSize: 25, color: "red", fontWeight: "bold" }}>
            ?
          </Text>
        </TouchableOpacity>
      </S.InfoContainer>

      <S.SmallContainer>
        <S.MiddleText>납입 금액</S.MiddleText>
        <InputSmall
          height={props.height}
          placeholder="금액을 입력하세요"
          value={irpCost}
          onChangeText={(text) => setIrpCost(text)}
          onPress={handleIrpPayment}
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
