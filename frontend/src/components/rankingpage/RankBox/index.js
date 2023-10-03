import React from "react";
import { Dimensions, View, Text } from "react-native";
import ImgBox from "../../ImgBox";
import * as S from "./style";

const { height } = Dimensions.get("window");

// 버튼 이름, 눌릴 때 함수, 배경 넣기
const RankBox = (props) => {
  return (
    <View style={{ justifyContent: "center" }}>
      <S.Container style={{ flex: 10 }}>
        <View style={{ flex: 2, alignItems: "center" }}>
          <Text style={{ fontSize: 30 }}>{rankNum}</Text>
        </View>
        <View style={{ flex: 3 }}>
          <ImgBox ProfileUrl={props.rankItem.userInfo.profileImg} />
        </View>
        <View style={{ flex: 3 }}>
          <Text>{props.rankItem.userInfo.name}</Text>
        </View>
        <View style={{ flex: 2 }}>
          <Text style={{ color: "#D90452" }}>
            {props.rankItem.resultInfo.assetGrowthRate}
          </Text>
        </View>
      </S.Container>
    </View>
  );
};

export default RankBox;
