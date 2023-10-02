import {
  StyleSheet,
  Text,
  View,
  StatusBar,
  Dimensions,
  TouchableOpacity,
  Image,
} from "react-native";
import React, { useState } from "react";
import InputLarge from "../../../../../../components/input/LargeInput";
import Button from "../../../../../../components/buttons/LargeButton";
// import BackArrow from '../../assets/BackArrow.png'
import * as S from "./style";
import ToggleButton from "./ToggleButton";
import { useNavigation } from "@react-navigation/native"; // useNavigation 추가

export default function GameStartPage() {
  const navigation = useNavigation();
  const handleGameMainPage = () => {
    navigation.navigate("Game", { screen: "GameMainPage" });
  };
  const [salary, setSalary] = useState(50000000);
  const [gender, setGender] = useState("MALE");
  // const { width, height } = Dimensions.get('window');
  const windowWidth = Dimensions.get("window").width;
  const windowHeight = Dimensions.get("window").Height;

  const ChangeSalary = (text) => {
    if (typeof text !== "string") {
      text = String(text);
    }
    const NumValue = text.replace(/[^0-9,]/g, "");
    // const numValue = parseInt(num.replace(/,/g, ''), 10);  // , 없애기

    //   if (num >= 25000000 && num <= 100000000) {
    //     setSalary(num);
    //     console.log(num)
    //     alert('잘 입력되었습니다')
    //   } else {
    //     console.log(num)
    //     alert('25,000,000 ~ 100,000,000 범위로 다시 설정해주세요.');
    //   }
    // };

    if (!isNaN(NumValue)) {
      console.log(NumValue);
      if (NumValue >= 25000000 && NumValue <= 100000000) {
        setSalary(NumValue); // 유효한 값이면 useState를 통해 저장
      } else {
        alert("다시 설정해주세요. (25,000,000 ~ 100,000,000)");
      }
    } else {
      alert("숫자를 입력해주세요.");
    }
  };

  const ChangeGender = (tmp) => {
    setGender(tmp);
  };

  return (
    // <Image style={{width: 20, height: 20}} source={BackArrow} />
    <View style={styles.container}>
      {/* <S.ImgContent
          source={BackArrow}
          /> */}
      {/* <Image source={BackArrow} styles={{width: 100}}></Image> */}
      <Text style={{ fontSize: 22, margin: 20 }}>
        {`  초기 연봉을 설정해주세요. 
(25,000,000 ~ 100,000,000)`}
      </Text>

      <InputLarge
        type="number"
        placeholder="50000000"
        // value={salary}
        value={salary.toString()}
      />

      <Text style={{ fontSize: 22, margin: 20 }}>성별을 설정해주세요.</Text>

      <View style={{ flexDirection: "row" }}>
        <ToggleButton
          label="남"
          isSelected={gender === "남"}
          onPress={() => ChangeGender("MALE")}
        />
        <ToggleButton
          label="여"
          isSelected={gender === "여"}
          onPress={() => ChangeGender("FEMALE")}
        />
      </View>
      <Button title="시작하기" onPress={handleGameMainPage} bgColor="#038C7F" />
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
