import React from "react";
import {
  View,
  Text,
  StatusBar,
  TouchableOpacity,
  ScrollView,
} from "react-native";
import * as S from "./style";
import ProfileImg from "../../../../assets/ProfileIcon.png";
import { Image } from "react-native-elements";
import RankBox from "../../../../components/rankingpage/RankBox";

const RankingPage = () => {
  return (
    <View style={{ flex: 1 }}>
      <StatusBar />

      <View
        style={{
          flex: 1,
          justifyContent: "center",
          alignItems: "center",
        }}
      >
        <Text style={{ fontSize: 25 }}>랭킹페이지</Text>
      </View>
      <View
        style={{
          flex: 10,
        }}
      >
        <ScrollView>
          <RankBox />
          <RankBox />
          <RankBox />
          <RankBox />
          <RankBox />
          <RankBox />
          <RankBox />
          <RankBox />
          <RankBox />
          <RankBox />
          <RankBox />
          <RankBox />
          <RankBox />
          <RankBox />
        </ScrollView>
      </View>
    </View>
  );
};

export default RankingPage;
