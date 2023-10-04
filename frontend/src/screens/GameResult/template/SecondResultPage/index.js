import React, { useRef, useEffect, useState } from "react";
import { View, Text, Image, StyleSheet, ScrollView, TouchableOpacity } from "react-native";
import Airplane from "../../../../assets/GameResultImg/Airplane.png";
import Car from "../../../../assets/GameResultImg/Car.png";
import Food from "../../../../assets/GameResultImg/Food.png";
import Golf from "../../../../assets/GameResultImg/Golf.png";
import ShareIcon from "../../../../assets/share.png";
import Button from "../../../../components/buttons/LargeButton";
import ViewShot from "react-native-view-shot";
import * as Sharing from "expo-sharing";
import style from "./style";
import { useNavigation } from "@react-navigation/native";
import axios from "axios";
import { useRecoilValue } from "recoil";
import { gameIdState } from "../../../../recoil/recoil";
import API_URL from "../../../../../config";


const SecondResultPage = (props) => {
  const gameId = props.route.params?.gameId || useRecoilValue(gameIdState);
  const [apiData2, setApiData2] = useState(null);
  useEffect(() => {
    console.log("요청시작");
    axios
      .get(`${API_URL}/api/game/result/${gameId}`)
      .then((response) => {        
        console.log("axios 요청 응답");
        console.log(response);
        setApiData2(response.data);
      })
      .catch((error) => {
        console.error("API 데이터를 가져오는 중 에러:", error);
        console.error("에러 상세 내용: ", error.response || error.message || error);
      });
  }, [gameId]);
  useEffect(() => {
    console.log("현재 상태의 값", apiData2);
  }, [apiData2]);
  const handleShare = async () => {
    try {
      // 캡처
      const uri = await viewRef.current.capture({
        format: "jpeg",
        quality: 0.8,
        result: "tmpfile",
        snapshotContentContainer: true,
        backgroundColor: "#FFFFFF",
      });

      // 이미지 공유
      await Sharing.shareAsync(Platform.OS === "ios" ? `file://${uri}` : uri, {
        mimeType: "image/jpeg",
        dialogTitle: "공유하기",
        UTI: "image/jpeg",
      });
    } catch (error) {
      console.error("캡처 및 공유 중 에러:", error);
    }
  };

  const viewRef = useRef();
  const navigation = useNavigation();

  const handleSecondResultPage = () => {
    navigation.navigate("Main");
  };
  
  if (!apiData2) {
    return <Text>Loading...</Text>;
  }
  const analysisComment = apiData2.data.analysisComment;
  const userInfo = apiData2.data.userInfo
  const oldAgeAssetsInfo = apiData2.data.oldAgeAssetsInfo;
  return (
    <ScrollView style={style.container}>
      {/* Share 버튼 */}
      <TouchableOpacity style={style.shareButton} onPress={handleShare}>
        <Image source={ShareIcon} style={style.shareIcon} />
      </TouchableOpacity>

      {/* 결과 분석표 */}
      <ViewShot ref={viewRef}>
        <View style={[style.section, style.marginBottom, { marginTop: 15 }]}>
          {/* 결과 분석표 구현 */}
          <Text style={{ fontSize: 21 }}>{`${userInfo.name} 님의 노후 생활비 분석`}</Text>
        </View>

        {/* 사용자 정보 */}
        <View style={[style.section, style.userInfo, style.marginBottom]}>
          <View>
            <Text style={[style.userName, { fontSize: 25 }]}>{`월 ${oldAgeAssetsInfo.monthlyAmount } 원`}</Text>
          </View>
        </View>

        {/* 그래프 */}
        <View style={[style.section, style.marginBottom]}>{/* 그래프 이미지 */}</View>

        {/* 이미지와 텍스트 */}
        <View style={[style.section, style.marginBottom]}>
          <View style={style.imageContainer}>
            <Image source={Airplane} style={style.icon} />
            <Text style={style.imageText}>{analysisComment.tripComment}</Text>
          </View>

          <View style={style.imageContainer}>
            <Image source={Car} style={style.icon} />
            <Text style={style.imageText}>{analysisComment.carComment}</Text>
          </View>

          <View style={style.imageContainer}>
            <Image source={Food} style={style.icon} />
            <Text style={style.imageText}>{analysisComment.eatOutComment}</Text>
          </View>

          <View style={style.imageContainer}>
            <Image source={Golf} style={style.icon} />
            <Text style={style.imageText}>{analysisComment.hobbyComment}</Text>
          </View>
        </View>
        {/* 총평 */}
        <View style={[style.section, style.userInfo, style.marginBottom, { marginTop: 20 }, { marginBottom: 30 }]}>
          <Text style={[style.userName, { fontSize: 23 }]}>
            <Text style={{ color: "#D90452" }}>{analysisComment.generalComment}</Text>
          </Text>
        </View>

        {/* 버튼 */}
        <View style={[style.section, style.marginBottom]}>
          {/* Replace with your button component */}
          <Button title="시작화면으로 돌아가기" onPress={handleSecondResultPage} />
        </View>
      </ViewShot>
    </ScrollView>
  );
};

export default SecondResultPage;
