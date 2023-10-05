import React, { useState, useEffect } from "react";
import { Ionicons } from "@expo/vector-icons";
import { ImageBackground, View, Text } from "react-native";
import { PageContainer, ToggleButton, TextContainer, DescriptionText } from "./style";
import axios from "axios";
import { useRecoilState, useRecoilValue } from "recoil";
import { gameIdState } from "../../../../recoil/recoil";
import API_URL from "../../../../../config";
import { refreshState } from "../../../../recoil/recoil";

function Toggle({ description, isExpanded, toggleSwitch }) {
  return (
    <ToggleButton onPress={toggleSwitch}>
      <Ionicons name={isExpanded ? "chevron-up" : "chevron-down"} size={24} color="black" />
      <TextContainer>
        <DescriptionText numberOfLines={isExpanded ? null : 1}>{description}</DescriptionText>
      </TextContainer>
    </ToggleButton>
  );
}

function NewsPage() {
  const [isExpanded, setIsExpanded] = useState([false, false, false, false, false, false]);
  const [refresh, setRefresh] = useRecoilState(refreshState);
  const toggleSwitch = (index) => {
    setIsExpanded((prevStates) => {
      const newState = [...prevStates];
      newState[index] = !newState[index];
      return newState;
    });
  };
  const gameID = useRecoilValue(gameIdState);
  const [data, setData] = useState([]);

  useEffect(() => {
    axios
      .get(`${API_URL}/api/news/${gameID}`)
      .then((response) => {
        setData(response.data.data.news);
        setRefresh(!refresh);
      })
      .catch((error) => {
        console.error("데이터를 가져오는 동안 오류 발생: ", error);
      });
  }, [refresh]);

  return (
    <ImageBackground
      source={require("../../../../assets/NewsBack.jpg")} // 이미지 경로를 설정하세요.
      style={{ flex: 1, resizeMode: "cover", justifyContent: "center" }}
    >
      <PageContainer>
      <View style={{ alignItems: 'center', justifyContent: 'center', marginBottom: 20 }}>
      <Text style={{ fontSize: 40, color: "#969797", fontWeight: "bold", textShadowColor: 'black', textShadowOffset: { width: 3, height: 3 }, textShadowOpacity: 0.9, textShadowRadius: 5 }}>
News Headlines</Text>
        </View>
        {data.map((description, index) => (
          <Toggle
            key={index}
            description={description}
            isExpanded={isExpanded[index]}
            toggleSwitch={() => toggleSwitch(index)}
          />
        ))}
      </PageContainer>
    </ImageBackground>
  );
}

export default NewsPage;
