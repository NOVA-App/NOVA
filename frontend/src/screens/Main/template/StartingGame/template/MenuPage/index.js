import React, { useEffect } from "react";
import { View, Text } from "react-native";
import ImgBox from "../../../../../../components/ImgBox";
import XXLargeButton from "../../../../../../components/buttons/XXLargeButton";
import * as S from "./style";
import { useNavigation } from "@react-navigation/native";
import axios from "axios";
import { useRecoilValue, useRecoilState } from "recoil";
import API_URL from "../../../../../../../config";
// import { accessTokenState, refreshTokenState } from "../../../../../../recoil/recoil";
import { tokenState, gameIdState } from "../../../../../../recoil/recoil";

const MenuPage = () => {
  // const [accessToken] = useRecoilState(accessTokenState);
  const accessToken = useRecoilValue(tokenState);
  const [gameId, setGameId] = useRecoilState(gameIdState);
  useEffect(() => {
    axios.defaults.headers.common["Authorization"] = `Bearer ${accessToken}`;
    axios
      .get(API_URL + "/api/game/inprogress")
      .then((response) => {
        // 요청이 성공했을 때 처리할 로직을 여기에 작성합니다.
        setGameId(response.data.data.gameId);
      })
      .catch((error) => {
        console.error("게임 진행 중 데이터를 가져오는 동안 오류 발생: ", error);
        setGameId(0);
      });
  }, []);

  const navigation = useNavigation();
  const handleGameStartPage = () => {
    navigation.navigate("GameStartPage");
  };
  const handleGameMainPage = () => {
    navigation.navigate("Game", { screen: "GameMainPage" });
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
        onPress={() => {
          if (gameId === 0) {
            handleGameStartPage();
          } else {
            handleGameMainPage();
          }
        }}
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
