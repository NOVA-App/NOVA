import React from "react";
import { View, Text, ScrollView, Image } from "react-native";
import Budget from "../../../budget";
import * as S from "./style";
import HouseImg from "../../../../assets/House.png";
import SmallButton from "../../../buttons/SmallButton";
const HouseCard = (props) => {
  return (
    <S.Container height={props.height}>
      <S.ImgBox source={HouseImg} />
      <S.ContentContainer>
        <S.MiddleText>{`마당 딸린 32평 주택`}</S.MiddleText>
        <S.MiddleText>{`금액     150,000,000`}</S.MiddleText>
        <S.MiddleText>{`남은 상환금   150,000,000`}</S.MiddleText>
        <View
          style={{
            marginTop: "2%",
            flexDirection: "row",
            justifyContent: "flex-end",
          }}
        >
          <SmallButton title="상환하기" bgColor="#0046FF" />
          {/* <Text>fdfd</Text> */}
        </View>
      </S.ContentContainer>
    </S.Container>
  );
};

export default HouseCard;
