import React from "react";
import { View, Text, ScrollView, Image } from "react-native";
// import * as S from "./style";
import SmallButton from "../../../../../components/buttons/SmallButton/index"

const InstallmentSavingsCard = (props) => {
  return (
    <S.Container height={props.height}>
      <S.ContentContainer>
        <S.MiddleText>{`총 투자금     150,000,000`}</S.MiddleText>
        <View
          style={{
            marginTop: "2%",
            flexDirection: "row",
            justifyContent: "flex-end",
          }}
        >
          <SmallButton title="가입하기" bgColor="#0046FF" />
          {/* <Text>fdfd</Text> */}
        </View>
      </S.ContentContainer>
    </S.Container>
  );
};

export default InstallmentSavingsCard;
