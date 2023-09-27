import React from "react";
import { View, Text, ScrollView, Image, Dimensions } from "react-native";
import * as S from "./style";
import SmallButton from "../buttons/SmallButton/index"
import MyInstallmentSavingsCard from "./MyInstallmentSavingsCard/index"

const { height, width } = Dimensions.get("window");

const MyInstallmentSavings = (props) => {
  return (
    <S.Container height={height * 0.4} >

        <S.TagContainer >
          <Text style={{ fontSize: 20, color: "white" }}>적금</Text>
        </S.TagContainer>
        <ScrollView style={{flex: 4}}> 
          <MyInstallmentSavingsCard />
          <MyInstallmentSavingsCard />
          <MyInstallmentSavingsCard />
          {/* <MyInstallmentSavingsCard /> */}
          {/* <MyInstallmentSavingsCard /> */}
          {/* <MyInstallmentSavingsCard /> */}
        </ScrollView>

        
        
        {/* <View
          style={{
            marginTop: "2%",
            flexDirection: "row",
            justifyContent: "flex-end",
          }}
        >
          <SmallButton title="가입하기" bgColor="#0046FF" />
        </View> */}
    </S.Container>
  );
};

export default MyInstallmentSavings;
