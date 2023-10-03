import React, { useState, useEffect } from "react";
import { View } from "react-native";
import * as S from "./style";
import HouseImg from "../../../../assets/House.png";
import SmallButton from "../../../buttons/SmallButton";
import LoanSmallModal from "../../../modals/LoanSmallModal";
import API_URL from "../../../../../config";
import axios from "axios";
import { gameDataState } from "../../../../recoil/recoil";
import { useRecoilValue } from "recoil";

const HouseCard = (props) => {
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [principalAmount, setPrincipalAmount] = useState(props.principal);
  const gameData = useRecoilValue(gameDataState);
  const usableAsset = gameData.annualAssets.usableAsset;

  const onModalClose = () => {
    setIsModalVisible(false);
  };

  const onModalOpen = () => {
    setIsModalVisible(true);
  };

  return (
    <S.Container height={props.height}>
      <S.ImgBox source={HouseImg} />
      <S.ContentContainer>
        <S.TextContainer>
          <S.MiddleText>{props.realtyName}</S.MiddleText>
        </S.TextContainer>
        <S.TextContainer>
          <S.MiddleText>{`금액`}</S.MiddleText>
          <S.MiddleText>{props.realtyPrice}</S.MiddleText>
        </S.TextContainer>
        <S.TextContainer>
          <S.MiddleText>{`남은 상환금`}</S.MiddleText>
          <S.MiddleText>{props.principal}</S.MiddleText>
        </S.TextContainer>
        <View
          style={{
            width: "100%",
            marginTop: "5%",
            flexDirection: "row",
            justifyContent: "flex-end",
          }}
        >
          <SmallButton
            title="상환하기"
            bgColor="#0046FF"
            onPress={onModalOpen}
          />
          <LoanSmallModal
            gameID={props.gameID}
            realtyId={props.realtyId}
            animationType="slide"
            transparent={true}
            visible={isModalVisible}
            title={`남은 대출금:  ${props.principal}\n
내 여유자금:  ${usableAsset}\n
대출 상환 신청 금액: `}
            onClose={onModalClose}
            maxAmount={usableAsset}
            minAmount={props.principal}
          />
        </View>
      </S.ContentContainer>
    </S.Container>
  );
};

export default HouseCard;
