import React, { useState } from "react";
import { Modal, Text, StyleSheet, View } from "react-native";
import * as S from "./style";
import Slider from "@react-native-community/slider";
import Button from "../../../buttons/MediumButton";
import {
  gameIdState,
  annualModalState,
  refreshState,
} from "../../../../recoil/recoil";
import { useRecoilState } from "recoil";
import API_URL from "../../../../../config";
import axios from "axios";

const AnnualModal = (props) => {
  const [sliderValue, setSliderValue] = useState(6000000);
  const [gameId] = useRecoilState(gameIdState);
  const [annualModal, setAnnualModal] = useRecoilState(annualModalState);
  const [refresh, setRefresh] = useRecoilState(refreshState);

  const closeModal = () => {
    setAnnualModal(false);
  };

  const handleSliderChange = (value) => {
    setSliderValue(value * 12);
  };

  const handleLivCostUpdate = () => {
    axios
      .patch(`${API_URL}/api/game/livcost`, {
        gameId: gameId,
        livingCost: sliderValue,
      })
      .then((response) => {
        closeModal();
        setRefresh(!refresh);
        console.log(refresh);
      })
      .catch((error) => {
        console.error("API 요청 오류:", error);
      });
  };

  const styles = StyleSheet.create({
    Text: {
      fontSize: 40,
      color: "black",
      marginRight: "2%",
    },
  });

  return (
    <Modal animationType="slide" transparent={true} visible={annualModal}>
      <S.ModalContent>
        <S.TitleContainer>
          <Text style={styles.Text} onPress={closeModal}>
            X
          </Text>
        </S.TitleContainer>
        <View
          style={{
            justifyContent: "center",
            minWidth: "100%",
            alignItems: "center",
          }}
        >
          <S.Text>여유 자금 :</S.Text>
          <S.Text>{[props.asset.usableAsset].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')}</S.Text>
          <S.Text>생활비 :</S.Text>
          <S.Text>
            {[props.asset.livingCost].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')}(연) {[props.asset.livingCost / 12].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')}(월)
          </S.Text>
          <S.Text>고정지출 :</S.Text>
          <S.Text>
            월세 -{[props.asset.fixedCost.monthlyRentCost].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')}(연) -
            {[props.asset.fixedCost.monthlyRentCost / 12].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')}(월)
          </S.Text>
          <S.Text>
            적금 고정 -{[props.asset.fixedCost.installmentSavingCost].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')}(연) -
            {[Math.floor(props.asset.fixedCost.installmentSavingCost / 12)].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')}(월)
          </S.Text>
          <View
            style={{
              minWidth: "100%",
              marginTop: "10%",
            }}
          >
            <S.Text>생활비 조정 (연): {[sliderValue].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')}</S.Text>
          </View>
          <Slider
            value={sliderValue}
            style={{ width: "100%", height: 40 }}
            minimumValue={500000}
            maximumValue={1000000}
            step={10000}
            minimumTrackTintColor="#D90452"
            maximumTrackTintColor="#000000"
            onValueChange={handleSliderChange}
          />
        </View>
        <View
          style={{
            justifyContent: "center",
            minWidth: "100%",
            alignItems: "center",
          }}
        >
          <Button title="확인" onPress={handleLivCostUpdate} />
        </View>
      </S.ModalContent>
    </Modal>
  );
};

export default AnnualModal;
