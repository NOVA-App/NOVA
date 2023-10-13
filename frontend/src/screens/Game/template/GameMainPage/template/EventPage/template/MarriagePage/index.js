import { StyleSheet, Text, View, Image } from "react-native";
import React from "react";
import { useNavigation } from "@react-navigation/native"; // useNavigation 추가
import Button from "../../../../../../../../components/buttons/SmallButton";
import Marriage from "../../../../../../../../assets/Marriage.png";
import axios from "axios";
import API_URL from "../../../../../../../../../config";
import { refreshState } from "../../../../../../../../recoil/recoil";
import { useRecoilState, useRecoilValue } from "recoil";
import { gameIdState } from "../../../../../../../../recoil/recoil";

export default function MarriagePage() {
  const navigation = useNavigation();
  const [refresh, setRefresh] = useRecoilState(refreshState);
  const gameId = useRecoilValue(gameIdState);
  const handleMarry = async () => {
    try {
      // POST 요청 보내기
      const response = await axios.post(API_URL + "/api/game/marry", {
        gameId: gameId,
      });
      if (response.status === 201) {
        console.log("결혼 성공");
        setRefresh(!refresh);
        alert("🎉🎉 결혼을 축하드립니다 🎉🎉");
        navigation.navigate("MainComponents");
      } else {
        alert("결혼은 한 번만 할 수 있습니다");
        console.error("결혼 요청 실패");
        navigation.navigate("MainComponents");
      }
    } catch (error) {
      alert("결혼은 한 번만 할 수 있습니다");
      console.error("POST 요청 중 오류 발생", error);
      navigation.navigate("MainComponents");
    }
  };
  const handleNotMarry = () => {
    navigation.navigate("MainComponents");
  };

  return (
    <View style={styles.container}>
      <View style={styles.content1}>
        <Text style={{ fontSize: 23, fontWeight: "bold" }}>결혼하기</Text>
        <Text style={{ fontSize: 15 }}>소요금액: 2000만원</Text>
      </View>
      <View style={styles.content2}>
        <Text style={{ fontSize: 22, marginBottom: 30 }}>
          결혼을 진행하시겠습니까?
        </Text>
        <View style={{ flexDirection: "row" }}>
          <View style={{ marginRight: 15 }}>
            <Button title="예" bgColor="#0046FF" onPress={handleMarry}></Button>
          </View>
          <View style={{ marginLeft: 15 }}>
            <Button
              title="아니오"
              bgColor="#D90452"
              onPress={handleNotMarry}
            ></Button>
          </View>
        </View>
      </View>
      <View style={styles.content3}>
        <Image source={Marriage} style={styles.img} />
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    padding: 10,
    flexDirection: "column",
  },
  content1: {
    flexDirection: "column",
    flex: 2,
    top: 20,
    alignItems: "center",
  },
  content2: {
    flexDirection: "column",
    flex: 2,
    alignItems: "center",
  },
  content3: {
    flexDirection: "column",
    flex: 3,
    alignItems: "center",
  },
  img: {
    width: 200,
    height: 200,
  },
});
