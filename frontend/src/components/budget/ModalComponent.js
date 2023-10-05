// ModalComponent.js

import React from "react";
import { View, Text, Modal, TouchableOpacity } from "react-native";
import { gameIdState } from "../../recoil/recoil";
import { useRecoilState } from "recoil";
import axios from "axios";
import API_URL from "../../../config";
import { useNavigation } from "@react-navigation/native";

const ModalComponent = ({ isVisible, onClose }) => {
  const [gameId, setGameId] = useRecoilState(gameIdState);
  const navigation = useNavigation();

  const handleGiveUp = async () => {
    try {
      await axios.delete(API_URL + `/api/game/${gameId}`);
      setGameId(0);
      onClose();
      navigation.replace("Main");
    } catch (error) {
      console.error("포기하기 요청 중 오류 발생:", error);
    }
  };
  
  const handleExit = () => {
    navigation.replace("Main")
  }

  return (
    <Modal visible={isVisible} animationType="slide" transparent={true}>
      <View style={{ flex: 1, justifyContent: "center", alignItems: "center" }}>
        <View
          style={{
            backgroundColor: "#eafaf5",
            padding: 20,
            borderRadius: 10,
            minWidth: 300,
            minHeight: "50%",
          }}
        >
          <TouchableOpacity onPress={handleGiveUp} style={{ flex: 1, justifyContent: "center", alignItems: "center" }}>
            <Text style={{ fontSize: 30 }}>포기하기</Text>
          </TouchableOpacity>

          <TouchableOpacity onPress={handleExit} style={{ flex: 1, justifyContent: "center", alignItems: "center" }}>
            <Text style={{ fontSize: 30 }}>나가기</Text>
          </TouchableOpacity>

          {/* X 아이콘을 터치할 때 모달을 닫음 */}
          <TouchableOpacity style={{ position: "absolute", top: 10, right: 10 }} onPress={onClose}>
            <Text>X</Text>
          </TouchableOpacity>
        </View>
      </View>
    </Modal>
  );
};

export default ModalComponent;
