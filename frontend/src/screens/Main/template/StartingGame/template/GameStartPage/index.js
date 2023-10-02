import { StyleSheet, Text, View, Dimensions } from "react-native";
import React, { useState } from "react";
import InputLarge from "../../../../../../components/input/LargeInput";
import Button from "../../../../../../components/buttons/LargeButton";
import ToggleButton from "./ToggleButton";
import { useNavigation } from "@react-navigation/native"; // useNavigation 추가
import { gameIdState } from "../../../../../../recoil/recoil";
import { useRecoilState } from "recoil";
import axios from "axios";
import API_URL from "../../../../../../../config";

export default function GameStartPage() {
  const navigation = useNavigation();

  const handleGameMainPage = () => {
    navigation.navigate("Game", { screen: "GameMainPage" });
  };
  const [salary, setSalary] = useState(50000000);
  const [gender, setGender] = useState("MALE");
  const [, setGameIdState] = useRecoilState(gameIdState);

  const gameStart = async () => {
    try {
      const response = await axios.post(`${API_URL}/api/game`, {
        startSalary: salary,
        gender: gender,
      });

      console.log("서버 응답 데이터:", response.data);
      setGameIdState(response.data.data.gameId);
      handleGameMainPage();
    } catch (error) {
      console.error("API 호출 오류:", error);
    }
  };

  const ChangeGender = (tmp) => {
    setGender(tmp);
  };

  return (
    <View style={styles.container}>
      <Text style={{ fontSize: 22, margin: 20 }}>
        {`  초기 연봉을 설정해주세요. 
(25,000,000 ~ 100,000,000)`}
      </Text>

      <InputLarge
        type="number"
        placeholder="50000000"
        value={salary.toString()}
        onChangeText={(text) => {
          const value = text.replace(/\D/g, ""); // 숫자 이외의 문자를 제거
          setSalary(value); // 업데이트된 값을 state에 설정
        }}
      />

      <Text style={{ fontSize: 22, margin: 20 }}>성별을 설정해주세요.</Text>

      <View style={{ flexDirection: "row" }}>
        <ToggleButton
          label="남"
          isSelected={gender === "MALE"}
          onPress={() => ChangeGender("MALE")}
        />
        <ToggleButton
          label="여"
          isSelected={gender === "FEMALE"}
          onPress={() => ChangeGender("FEMALE")}
        />
      </View>
      <Button title="시작하기" onPress={gameStart} bgColor="#038C7F" />
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
});
