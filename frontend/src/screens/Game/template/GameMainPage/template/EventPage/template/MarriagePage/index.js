import { StyleSheet, Text, View, Image } from "react-native";
import React, { useState } from "react";
import { useNavigation } from "@react-navigation/native"; // useNavigation 추가
import Button from "../../../../../../../../components/buttons/SmallButton";
import Marriage from "../../../../../../../../assets/Marriage.png";
import axios from "axios";

export default function MarriagePage() {
  const navigation = useNavigation(); // 네비게이션 객체 생성
  const [isMarried, setIsMarried] = useState(false);
  const handleMarry = async () => {
    try {
      // POST 요청 보내기
      const response = await axios.post(
        "http://192.168.56.200:8080/api/game/marry",
        {
          gameId: 2,
        }
      );
      if (response.status === 201) {
        console.log("결혼 성공");
      } else {
        console.error("결혼 요청 실패");
      }
    } catch (error) {
      console.error("POST 요청 중 오류 발생", error);
    }
  };
  // 나중에 recoil에 저장하기
  const handleNext = () => {
    //
    navigation.navigate("LoginPage"); // 'LoginPage'로 변경
  };

  // const { width, height } = Dimensions.get('window');
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
            <Button title="아니오" bgColor="#D90452"></Button>
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
    // backgroundColor: 'blue',
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
    // top: 20,
    alignItems: "center",
  },
  content3: {
    flexDirection: "column",
    flex: 3,
    // top: 20,
    alignItems: "center",
  },
  img: {
    width: 200,
    height: 200,
  },
});
