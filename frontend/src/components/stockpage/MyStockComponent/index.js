import React from "react";
import { Dimensions, View, Text, ScrollView } from "react-native";
import StockCard from "./StockCard";
import * as S from "./style";

const { height } = Dimensions.get("window");

const MyStock = () => {
  return (
    <View style={{ flex: 1, minWidth: "90%" }}>
      <S.Container style={{ flex: 8.5 }}>
        <View
          style={{
            flex: 0.5,
            paddingLeft: "5%",
            justifyContent: "flex-start",
          }}
        >
          <S.TagContainer>
            <Text style={{ fontSize: 20, color: "white" }}>보유 주식</Text>
          </S.TagContainer>
        </View>
        <View style={{ flex: 8 }}>
          <View>
            <S.TotalAssetContainer>
              <S.MiddleText>{`총 투자금                      150,000,000`}</S.MiddleText>
              <S.MiddleText>{`총 평가금                      150,000,000`}</S.MiddleText>
              <S.MiddleText>{`월세 수익                      150,000,000`}</S.MiddleText>
            </S.TotalAssetContainer>
          </View>
          <View style={{ marginTop: "5%" }}>
            <ScrollView>
              <StockCard height={height} />
            </ScrollView>
          </View>
        </View>
      </S.Container>
    </View>
  );
};

export default MyStock;
