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
import { useNavigation } from "@react-navigation/native";

const MyPage = () => {
  const [userInfo, setUserInfo] = useState({});
  const [userToken] = useRecoilState(accessTokenState);
  const [refresh, setRefresh] = useState(false);
  const [newName, setNewName] = useState(""); // 이름 변경을 위한 state 추가
  const [myGame, setMyGame] = useState([]);
  const navigation = useNavigation();

  const handleGameDetailNavigation = (gameId) => {
    navigation.navigate("GameResult", {
      screen: "FirstResultPage",
      params: { gameId }
    });  };

  useEffect(() => {
    axios
      .get(API_URL + "/api/user")
      .then((response) => {
        setUserInfo(response.data.data);
      })
      .catch((error) => {
        console.error("데이터를 가져오는 동안 오류 발생: ", error);
      });
  }, [refresh]);

  useEffect(() => {
    axios
      .get(API_URL + "/api/game/myresults")
      .then((response) => {
        setMyGame(response.data.data.myResults);
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
        const formData = new FormData();
        formData.append("profile", {
          uri: localUri,
          name: "profile.jpg",
          type: "image/jpeg",
        });
        await axios({
          method: "put",
          url: `${API_URL}/api/user/profileimg`,
          headers: {
            Authorization: `Bearer ${userToken}`,
            "Content-Type": "multipart/form-data",
          },
          data: formData,
        });
        setRefresh(!refresh);
      }
    } catch (error) {
      console.error("axios 요청 중 에러 발생:", error);
    }
  };

  const handleNameChange = async () => {
    try {
      // 이름 변경을 위한 API 요청 보내기
      await axios.patch(`${API_URL}/api/user?name=${newName}`);
      setRefresh(!refresh);
    } catch (error) {
      console.error("이름 변경 중 에러 발생:", error);
    }
  };

  const deleteUser = async () => {
    try {
      const response = await axios.delete(`${API_URL}/api/user`);
      return response.data;
    } catch (error) {
      throw error;
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
            source={{ uri: userInfo.profileImg }}
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
          <S.Input
            placeholder={userInfo ? userInfo.name : ""}
            value={newName}
            onChangeText={(text) => {
              setNewName(text);
              console.log(newName);
            }}
          />
          <View
            style={{
              flexDirection: "row",
              width: "35%",
              height: "5%",
              marginTop: "3%",
            }}
          >
            <MediumButton
              title="변경"
              bgColor="#038C7F"
              onPress={handleNameChange}
            />
            <MediumButton title="탈퇴" bgColor="#D90452" onPress={deleteUser} />
          </View>
        </View>
      </View>
      <Text style={{ fontSize: 25, fontWeight: "bold", marginLeft: "3%", marginBottom: "2%" }}>전적</Text>
      <View style={{ flex: 10, alignItems: "center" }}>
        {myGame.length > 0 &&
          myGame.map((game, index) => (
            <View style={{ marginBottom: "1%" }} key={index}>
              <Button
              title={`${index + 1}번 게임
              ${game.startSalary}원 ➜ ${game.resultAssets}원
              변동치 : ${game.assetGrowthRate}%`}
              onPress={() => handleGameDetailNavigation(game.gameId)}
              style={{ textAlign: 'left', marginLeft: 10 }}
            />
            </View>
          ))}
        <View style={{ marginBottom: "1%" }}></View>
        <View style={{ marginBottom: "1%" }}></View>
      </View>
    </View>
  );
};

export default MyPage;