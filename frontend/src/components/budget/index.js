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
      <View style={styles.logoSection}>
        <TouchableOpacity onPress={toggleModal}>
          <Image
            style={{
              resizeMode: "contain",
              width: 80,
              height: 80,
              marginLeft: 5,
            }}
            source={require("../../assets/nova_logo.png")}
          />
        </TouchableOpacity>
      </View>
      <View style={styles.centerContent}>
        <Text style={{ fontSize: 24, fontWeight: "bold" }}>여유자금</Text>
        <Text style={{ fontSize: 20, color: "#F5B700" }}>
          {[data.annualAssets.usableAsset].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')
}원
        </Text>
      </View>
      <View style={styles.menuSection}>
        <TouchableOpacity onPress={toggleModal}>
          <Text style={{ fontSize: 17 }}>메뉴</Text>
        </TouchableOpacity>
      </View>
      <ModalComponent isVisible={isModalVisible} onClose={toggleModal} />
    </StyledUpper>
  );
};

const styles = {
  logoSection: {
    flex: 1,
    justifyContent: "center",
    alignItems: "flex-start",
  },
  logo: {
    resizeMode: "contain",
    width: "100%",
    marginLeft: 5,
  },
  centerContent: {
    alignItems: "center",
    flex: 3,
    justifyContent: "center",
  },
  menuSection: {
    flex: 1,
    justifyContent: "center",
    alignItems: "flex-end",
  },
};

const StyledUpper = styled.View`
  background-color: white;
  padding: 10px;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  /* height: 100px; */ /* 원하는 높이로 조절 */
`;

export default Budget;
