import React, { useEffect, useState } from "react";
import { View, Text, StatusBar, ScrollView } from "react-native";
import * as S from "./style";
import ProfileImg from "../../../../assets/ProfileIcon.png";
import { Image } from "react-native-elements";
import RankBox from "../../../../components/rankingpage/RankBox";
import axios from "axios";

const RankingPage = () => {
  const [data, setData] = useState([]);
  useEffect(() => {
    axios
      .get("http://192.168.56.200/api/game/rank") // 게임아이디 받아와서 주기
      .then((response) => {
        setData(response.data.data);
        console.log(data);
      })
      .catch((error) => {
        console.error("데이터를 가져오는 동안 오류 발생: ", error);
      });
  }, []);

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
          {data.length > 0 && // 데이터가 존재하는 경우에만 매핑
            data.map((rankItem, index) => (
              <RankBox key={index} rankItem={rankItem} rankNum={index + 1} />
            ))}
        </ScrollView>
      </View>
    </View>
  );
};

export default RankingPage;
