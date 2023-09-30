import React, { useState, useEffect } from "react";
import { Ionicons } from "@expo/vector-icons";
import {
  PageContainer,
  ToggleButton,
  TextContainer,
  DescriptionText,
} from "./style";
import axios from "axios";
function Toggle({ description, isExpanded, toggleSwitch }) {
  return (
    <ToggleButton onPress={toggleSwitch}>
      <Ionicons
        name={isExpanded ? "chevron-up" : "chevron-down"}
        size={24}
        color="black"
      />
      <TextContainer>
        <DescriptionText numberOfLines={isExpanded ? null : 1}>
          {description}
        </DescriptionText>
      </TextContainer>
    </ToggleButton>
  );
}

function NewsPage() {
  const [isExpanded, setIsExpanded] = useState([
    false,
    false,
    false,
    false,
    false,
    false,
  ]);

  const toggleSwitch = (index) => {
    setIsExpanded((prevStates) => {
      const newState = [...prevStates];
      newState[index] = !newState[index];
      return newState;
    });
  };

  const [data, setData] = useState([]);

  useEffect(() => {
    axios
      .get("http://192.168.56.200:8080/api/news/1") // 게임아이디 받아와서 주기
      .then((response) => {
        setData(response.data.data.news);
      })
      .catch((error) => {
        console.error("데이터를 가져오는 동안 오류 발생: ", error);
      });
  }, []);

  return (
    <PageContainer>
      {data.map((description, index) => (
        <Toggle
          key={index}
          description={description}
          isExpanded={isExpanded[index]}
          toggleSwitch={() => toggleSwitch(index)}
        />
      ))}
    </PageContainer>
  );
}

export default NewsPage;
