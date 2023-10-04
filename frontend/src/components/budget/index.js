import React, { useState } from "react";
import { Text, View, Image, TouchableOpacity } from "react-native";
import Logo from "../../assets/nova_logo.png";
import styled from "styled-components/native";
import { gameDataState } from "../../recoil/recoil";
import { useRecoilState } from "recoil";
import ModalComponent from "./ModalComponent";

const Budget = () => {
  const [data] = useRecoilState(gameDataState);
  const [isModalVisible, setModalVisible] = useState(false);

  const toggleModal = () => {
    setModalVisible(!isModalVisible);
  };

  return (
    <StyledUpper>
      <TouchableOpacity onPress={toggleModal}>
        <View style={{ flex: 1, alignItems: "flex-start" }}>
          <Image
            style={{ resizeMode: "contain", width: "70%", marginLeft: 5, marginTop:3 }}
            source={Logo}
          />
        </View>
      </TouchableOpacity>
      <View style={{ alignItems: "center", flex: 1, justifyContent: "center" }}>
        <Text style={{ fontSize: 24, fontWeight: "bold" }}>여유자금</Text>
        <Text style={{ fontSize: 20, color: "#F5B700" }}>
          {data.annualAssets.usableAsset}원
        </Text>
      </View>
      <TouchableOpacity onPress={toggleModal}>
        <View
          style={{ flex: 1, alignItems: "flex-end", justifyContent: "center" }}
        >
          <Text style={{ fontSize: 17 }}>메뉴</Text>
        </View>
      </TouchableOpacity>
      <ModalComponent isVisible={isModalVisible} onClose={toggleModal} />
    </StyledUpper>
  );
};

export default Budget;

const StyledUpper = styled.View`
  flex-direction: row;
  background-color: white;
  padding: 10px;
  align-items: center;
  justify-content: space-between;
  height: 100px; /* 원하는 높이로 조절 */
`;
