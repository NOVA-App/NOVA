import React from "react";
import { View, Text } from "react-native";
import ImgBox from "../../../../../../components/ImgBox";
import XXLargeButton from "../../../../../../components/buttons/XXLargeButton";
import * as S from "./style";
import { useNavigation } from "@react-navigation/native";
import axios from "axios";
import { useRecoilState } from "recoil";
import { accessTokenState, refreshTokenState } from "../../../../../../recoil/recoil";

const MenuPage = () => {
  const [accessToken] = useRecoilState(accessTokenState);
  console.log("여기에 찍혀야댐")
  console.log(accessToken)

  axios.defaults.headers.common["Authorization"] = `Bearer ${accessToken}`;

  const navigation = useNavigation();
  const handleGameStartPage = () => {
    navigation.navigate("GameStartPage");
  };
  const handleMyPage = () => {
    navigation.navigate("MyPage");
  };
  const handleRankingPage = () => {
    navigation.navigate("RankingPage");
  };
  return (
    <View style={{ alignItems: "center" }}>
      <S.Container style={{ alignItems: "center" }}>
        <View
          style={{
            marginLeft: "5%",
            marginRight: "5%",
            justifyContent: "center",
            alignItems: "center",
          }}
        >
          <ImgBox />
        </View>
        <View style={{ width: "50%", justifyContent: "center" }}>
          <Text style={{ fontSize: 20 }}>A310님 환영합니다!</Text>
        </View>
      </S.Container>
      <XXLargeButton
        style={{ margin: 10 }}
        bgColor="#038C7F"
        title="새로운 게임 시작하기"
        onPress={handleGameStartPage}
      />
      <XXLargeButton
        style={{ margin: 10 }}
        bgColor="#F5B700"
        title="마이페이지"
        onPress={handleMyPage}
      />
      <XXLargeButton
        style={{ margin: 10 }}
        bgColor="#D90452"
        title="랭킹페이지"
        onPress={handleRankingPage}
      />
    </View>
  );
};

export default MenuPage;
