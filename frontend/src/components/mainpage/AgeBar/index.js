import React from "react";
import { Dimensions, View, Text, Image } from "react-native";
import SmallButton from "../../../../src/components/buttons/SmallButton";
import RunnerIcon from "../../../assets/RunnerIcon.png";
import * as S from "./style";

const { width, height } = Dimensions.get("window");

// 버튼 이름, 눌릴 때 함수, 배경 넣기
const AgeBar = (props) => {
  return (
    <View style={{ marginTop: "1%" }}>
      <S.BarContainer height={height} width={width} bgColor="transparent">
        <S.ProgressBar bgColor="transparent">
          <Text style={{ fontSize: 20, fontWeight: "bold" }}>27</Text>
        </S.ProgressBar>
      </S.BarContainer>

      <S.Container>
        <S.BarContainer height={height} width={width}>
          <S.ProgressBar>
            <View>
              <Image source={RunnerIcon} />
            </View>
          </S.ProgressBar>
        </S.BarContainer>
        <SmallButton title="내년으로..." />
      </S.Container>
    </View>
  );
};

export default AgeBar;