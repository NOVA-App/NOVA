import React from "react";
import { Dimensions, View, Text, ScrollView } from "react-native";
import Budget from "../../../components/budget";
import HouseCard from "../../../components/realestatepage/HouseCard";
import * as S from "./style";

const { height } = Dimensions.get("window");

const MyRealEstatePage = () => {
  return (
    <View style={{ flex: 1 }}>
      <View style={{ flex: 1 }}>
        <Budget />
      </View>
      <View style={{ flex: 9, backgroundColor: "#CDE8E5" }}>
        <View style={{ flex: 1 }}></View>
        <S.Container style={{ flex: 8.5 }}>
          <View
            style={{
              flex: 0.5,
              paddingLeft: "5%",
              justifyContent: "flex-start",
            }}
          >
            <S.TagContainer>
              <Text style={{ fontSize: 20, color: "white" }}>
                내 부동산 현황
              </Text>
            </S.TagContainer>
          </View>
          <View style={{ flex: 8 }}>
            <View style={{ height: "25%" }}>
              <S.TotalAssetContainer>
                <S.MiddleText>{`총 투자금                      150,000,000`}</S.MiddleText>
                <S.MiddleText>{`총 평가금                      150,000,000`}</S.MiddleText>
                <S.MiddleText>{`월세 수익                      150,000,000`}</S.MiddleText>
              </S.TotalAssetContainer>
            </View>
            <ScrollView>
              <S.CenterView>
                <HouseCard height={height} />
              </S.CenterView>
              <HouseCard height={height} />
              <HouseCard height={height} />
              <HouseCard height={height} />
              <HouseCard height={height} />
              <HouseCard height={height} />
            </ScrollView>
          </View>
        </S.Container>
      </View>
    </View>
  );
};

export default MyRealEstatePage;
