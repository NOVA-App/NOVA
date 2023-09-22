import React from "react";
import { Dimensions, View, Text, ScrollView } from "react-native";
import HouseCard from "./HouseCard";
import * as S from "./style";

const { height } = Dimensions.get("window");

const ForSaleEstate = () => {
  return (
    <View style={{ flex: 1 }}>
      <S.Container style={{ flex: 8.5 }}>
        <View
          style={{
            flex: 0.5,
            paddingLeft: "5%",
            justifyContent: "flex-start",
          }}
        >
          <S.TagContainer>
            <Text style={{ fontSize: 20, color: "white" }}>내 부동산 현황</Text>
          </S.TagContainer>
        </View>
        <View style={{ flex: 8 }}>
          <View style={{ marginTop: "5%" }}>
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
        </View>
      </S.Container>
    </View>
  );
};

export default ForSaleEstate;
