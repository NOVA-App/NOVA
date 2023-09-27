import React from "react";
import { View, Text, ScrollView, Image, Dimensions } from "react-native";
import * as S from "./style";
import SmallButton from "../buttons/SmallButton/index"
import InputSmall from "../input/SmallInput";

const { height, width } = Dimensions.get("window");

const MyIRP = (props) => {
  return (
    <S.Container height={height * 0.4} >

        <S.TagContainer >
          <Text style={{ fontSize: 20, color: "white" }}>IRP</Text>
        </S.TagContainer>

        <S.SmallContainer>
          <S.MiddleText>현재금액</S.MiddleText>
            <InputSmall height={props.height}/>
        </S.SmallContainer >
        

        
        
        <View
          style={{
            marginTop: "2%",
            flexDirection: "row",
            justifyContent: "flex-end",
          }}
        >
          <SmallButton title="현재 금액" bgColor="#D90452" />
          {/* <Text>fdfd</Text> */}
        </View>
    </S.Container>
  );
};

export default MyIRP;
