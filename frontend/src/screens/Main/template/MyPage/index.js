import React, { useState } from "react";
import { View, Text, StatusBar, Pressable } from "react-native";
import Button from "../../../../components/buttons/XLargeButton";
import MediumButton from "../../../../components/buttons/MediumButton";
import * as S from "./style";
import ProfileImg from "../../../../assets/ProfileIcon.png";
import * as ImagePicker from "expo-image-picker";

const MyPage = () => {
  const [profileImage, setProfileImage] = useState(null);

  // 이미지를 선택하고 선택한 이미지를 처리하는 함수
  const handleSelectImage = async () => {
    try {
      const result = await ImagePicker.launchImageLibraryAsync({
        mediaTypes: ImagePicker.MediaTypeOptions.Images,
        allowsEditing: true,
        aspect: [1, 1], // 원하는 이미지 비율로 설정
        quality: 1, // 이미지 품질 (0부터 1까지)
      });

      if (!result.cancelled) {
        setProfileImage(result.uri); // 선택한 이미지를 state에 저장
      }
    } catch (error) {
      console.error("이미지 선택 오류: ", error);
    }
  };

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
          <Pressable
            style={{
              position: "relative",
              bottom: "5%", // 원하는 위치로 조절하세요.
              right: "-15%", // 원하는 위치로 조절하세요.
              backgroundColor: "#F8FA9D", // 버튼 배경색
              borderRadius: 10,
              paddingRight: 10,
              paddingLeft: 10,
            }}
            onPress={handleSelectImage}
          >
            <Text style={{ fontSize: 30 }}>+</Text>
          </Pressable>
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
