import { StyleSheet, Text, View, Image } from "react-native";
import React, { useState } from "react";
import { useNavigation } from "@react-navigation/native"; // useNavigation 추가
import Button from "../../../../../../../../components/buttons/SmallButton";
import Baby from "../../../../../../../../assets/Baby.png";
import { isChildBirthState } from "../../../../../../../../recoil/recoil";
import { useRecoilState } from "recoil";
export default function ChildPage() {
  const navigation = useNavigation(); // 네비게이션 객체 생성
  const [isChildBirth, setIsChildBirth] = useRecoilState(isChildBirthState);
  const handleNext = () => {
    navigation.navigate("LoginPage"); // 'LoginPage'로 변경
  };
  const handleChildButtonTrue = () => {
    setIsChildBirth(true);
    console.log(isChildBirth);
    alert("✨✨ 출산을 축하드립니다 ✨✨");
    navigation.navigate("MainComponents")
  };
  const handleNotChildButtonTrue = () => {
    navigation.navigate("MainComponents")
  }
  return (
    <View style={styles.container}>
      <View style={styles.content1}>
        <Text style={{ fontSize: 23, fontWeight: "bold" }}>자녀 낳기</Text>
        <Text style={{ fontSize: 20 }}>매월 70만원 생활비 추가 소비</Text>
        <Text style={{ fontSize: 20 }}>노후시 50만원 용돈</Text>
      </View>
      <View style={styles.content2}>
        <Text style={{ fontSize: 22, marginBottom: 30 }}>
          자녀를 가지시겠습니까?
        </Text>
        <View style={{ flexDirection: "row" }}>
          <View style={{ marginRight: 15 }}>
            <Button
              title="예"
              bgColor="#0046FF"
              onPress={handleChildButtonTrue}
            ></Button>
          </View>
          <View style={{ marginLeft: 15 }}>
            <Button title="아니오" bgColor="#D90452" onPress={handleNotChildButtonTrue}
></Button>
          </View>
        </View>
      </View>
      <View style={styles.content3}>
        <Image style={styles.img} source={Baby} />
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
