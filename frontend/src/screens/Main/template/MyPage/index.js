import React, { useState, useEffect } from "react";
import { View, Text, StatusBar, Pressable } from "react-native";
import Button from "../../../../components/buttons/XLargeButton";
import MediumButton from "../../../../components/buttons/MediumButton";
import * as S from "./style";
import ProfileImg from "../../../../assets/ProfileIcon.png";
import axios from "axios";
import API_URL from "../../../../../config";
import * as ImagePicker from "expo-image-picker";
import { accessTokenState } from "../../../../recoil/recoil";
import { useRecoilState } from "recoil";

const MyPage = () => {
  const [userInfo, setUserInfo] = useState({});
  const [userToken] = useRecoilState(accessTokenState);
  const [imageUri, setImageUri] = useState("");

  useEffect(() => {
    axios
      .get(API_URL + "/api/user")
      .then((response) => {
        setUserInfo(response.data.data);
      })
      .catch((error) => {
        console.error("데이터를 가져오는 동안 오류 발생: ", error);
      });
  }, []);

  const uploadImage = async () => {
    try {
      const result = await ImagePicker.launchImageLibraryAsync({
        mediaTypes: ImagePicker.MediaTypeOptions.All,
        allowsEditing: true,
        aspect: [1, 1],
        quality: 1,
      });

      if (!result.canceled) {
        const localUri = result.assets[0].uri;
        console.log(localUri);
        const formData = new FormData();
        formData.append("profile", { uri: localUri });
        console.log(formData);
        await axios({
          method: "put",
          url: `${API_URL}/api/user/profileimg`,
          headers: {
            Authorization: `Bearer ${userToken}`,
            "Content-Type": "multipart/form-data",
          },
          data: formData,
        });
      }
    } catch (error) {
      console.error("axios 요청 중 에러 발생:", error);
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
          <S.ImgContainer
            source={
              userInfo && userInfo.profileImg
                ? {
                    uri: `${API_URL}/profiles/${userInfo.profileImg}`,
                  }
                : ProfileImg
            }
          />
          <Pressable
            style={{
              position: "relative",
              bottom: "5%",
              right: "-15%",
              backgroundColor: "#F8FA9D",
              borderRadius: 10,
              paddingRight: 10,
              paddingLeft: 10,
            }}
            onPress={uploadImage}
          >
            <Text style={{ fontSize: 30 }}>+</Text>
          </Pressable>
          <Text style={{ fontSize: 26 }}>{userInfo ? userInfo.name : ""}</Text>
          <View
            style={{
              flexDirection: "row",
              width: "35%",
              height: "5%",
              marginTop: "3%",
            }}
          >
            <MediumButton title="변경" bgColor="#038C7F" />
            <MediumButton title="탈퇴" bgColor="#D90452" />
          </View>
        </View>
      </View>
      <View style={{ flex: 10, alignItems: "center" }}>
        <Button />
      </View>
    </View>
  );
};

export default MyPage;
