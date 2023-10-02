import React from "react";
import { View } from "react-native";
import * as S from "./style";
import HouseImg from "../../../../assets/House.png";
import SmallButton from "../../../buttons/SmallButton";
import OneButtonSmallModal from "../../../modals/OneButtonSmallModal";


const HouseCard = (props) => {
  const [modalVisible, setModalVisible] = useState(false);
  const [principalAmount, setPrincipalAmount] = useStat(props.principal);
  
  const handleRepayment = () => {
    axios
    .patch(API_URL + "/api/realty/loan/repayment", {
      gameId: props.gameId, // 게임 아이디, 필요한 경우 수정
      realtyId: props.realtyId,
      principalAmount: updatedPrincipalAmount,
    })
    .then((response) => {
      console.log("PATCH 요청 성공!!:", response.data);
    })
    .catch((error) => {
      console.error("PATCH 요청 오류 ㅠㅅㅠ:", error);
    });
    setModalVisible(false);
  }


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
          <SmallButton title="상환하기" bgColor="#0046FF" onPress={() => setModalVisible(true)}/>
          <OneButtonSmallModal
            animationType="slide"
            transparent={true}
            visible={modalVisible}
            title='상환하기'
            onClose={() => {
              setModalVisible(false);
        }}
      />
          
        </View>
      </S.ContentContainer>
    </S.Container>
  );
};

export default HouseCard;
