import React from "react";
import { View, Text, StatusBar, TouchableOpacity } from "react-native";
import Button from "../../../../components/buttons/XLargeButton";
import MediumButton from "../../../../components/buttons/MediumButton";

import * as S from "./style";
import ProfileImg from "../../../../assets/ProfileIcon.png";
import { Image } from "react-native-elements";

const MyPage = () => {
  return (
    <View style={{ flex: 20 }}>
      <StatusBar />
      <View style={{ flex: 10 }}>
        <View
          style={{
            flex: 5,
            justifyContent: "center",
            alignItems: "center",
          }}
        >
          <S.ImgContainer source={ProfileImg} />
          <TouchableOpacity
            style={{
              position: "relative",
              bottom: "5%", // 원하는 위치로 조절하세요.
              right: "-15%", // 원하는 위치로 조절하세요.
              backgroundColor: "#F8FA9D", // 버튼 배경색
              borderRadius: 10,
              paddingRight: 10,
              paddingLeft: 10,
            }}
            onPress={() => {
              // 버튼 클릭 시 실행할 동작을 여기에 추가하세요.
            }}
          >
            <Text style={{ fontSize: 30 }}>+</Text>
          </TouchableOpacity>
          <Text style={{ fontSize: 26 }}>이름임</Text>
          <View
            style={{
              flexDirection: "row",
              display: "realtive",
              bottom: 35,
              left: 125,
              width: "35%",
              height: "5%",
            }}
          >
            <MediumButton title="변경" bgColor="#038C7F" />
            <MediumButton title="탈퇴" bgColor="#D90452" />
          </View>
          <Text style={{ fontSize: 20, color: "#828282" }}>
            email@gmail.com
          </Text>
        </View>
      </View>
      <View style={{ flex: 10, alignItems: "center" }}>
        <Button />
      </View>
    </View>
  );
};

export default MyPage;
