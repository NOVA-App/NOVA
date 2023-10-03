import React, { useEffect, useState } from "react";
import { View, Image, TouchableOpacity } from "react-native";
import AgeBar from "../../../../../../../../components/mainpage/AgeBar";
import AnnualAsset from "../../../../../../../../components/mainpage/AnnualAsset";
import MyAsset from "../../../../../../../../components/mainpage/MyAsset";
import { style } from "./style";
import BabyButton from "../../../../../../../../components/buttons/EventButton/BabyButton";
import MarriageButton from "../../../../../../../../components/buttons/EventButton/MarriageButton";
import { useNavigation } from "@react-navigation/native";
import {
  gameIdState,
  isChildBirthState,
  gameDataState,
  annualModalState,
} from "../../../../../../../../recoil/recoil";
import { useRecoilState } from "recoil";
import API_URL from "../../../../../../../../../config";
import axios from "axios";
import AnnualModal from "../../../../../../../../components/mainpage/modal/annualModal";

const MainComponents = () => {
  const [gameId] = useRecoilState(gameIdState);
  const [gameData, setGameData] = useRecoilState(gameDataState);
  const [isChildBirth, setIsChildBirth] = useRecoilState(isChildBirthState);
  const navigation = useNavigation();
  const [refresh, setRefresh] = useState(false);
  const [modalVisible] = useRecoilState(annualModalState);

  useEffect(() => {
    axios
      .get(`${API_URL}/api/game/${gameId}`)
      .then((response) => {
        const responseData = response.data;
        
        if (responseData.message === "게임 종료") {
          // 게임 종료일 경우 다른 페이지로 이동
          navigation.navigate("FirstResultPage"); // 적절한 페이지로 변경
        } else {
          setGameData(responseData.data);
        }
      })
      .catch((error) => {
        console.error("데이터를 가져오는 동안 오류 발생: ", error);
      });
  }, [refresh]);

  // 다음해로 넘어가기 버튼 클릭
  const handleNextYearButtonClick = () => {
    axios
      .put(`${API_URL}/api/game`, {
        gameId: gameId,
        isChildBirth: isChildBirth,
      })
      .then((response) => {
        // API 요청이 성공하면 isChildBirth 값을 업데이트합니다.
        setIsChildBirth(false);
        navigation.navigate("MainComponents");
        setRefresh(!refresh);
      })
      .catch((error) => {
        console.error("API 요청 오류:", error);
      });
      };

  const handleBabyButtonClick = () => {
    navigation.navigate("EventPage", { screen: "ChildPage" });
  };

  const handleMarriageButtonClick = () => {
    navigation.navigate("EventPage", { screen: "MarriagePage" });
  };

  return (
    <View style={style.container}>
      <AgeBar age={gameData.currentAge} onPress={handleNextYearButtonClick} />
      <AnnualAsset asset={gameData.annualAssets} />
      <AnnualModal
        visible={modalVisible}
        asset={gameData.annualAssets}
        setRefresh={setRefresh}
      />
      <MyAsset asset={gameData.myAssets} />
      <View style={style.imageContainer}>
        <View style={style.imageAndButtonContainer}>
          <Image
            source={{
              uri: "https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/People/Man%20Raising%20Hand.png",
            }}
            style={style.image}
          />
          <View style={style.buttonContainer}>
            <TouchableOpacity style={{ position: "relative" }}>
              <MarriageButton onPress={handleMarriageButtonClick} />
            </TouchableOpacity>
            <View style={{ marginVertical: 15 }}>
              <TouchableOpacity style={{ position: "relative" }}>
                <BabyButton onPress={handleBabyButtonClick} />
              </TouchableOpacity>
            </View>
          </View>
        </View>
      </View>
    </View>
  );
};

export default MainComponents;
