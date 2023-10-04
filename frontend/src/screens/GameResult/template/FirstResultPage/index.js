import React, { useRef, useEffect, useState } from "react";
import { View, Text, Image, ScrollView, TouchableOpacity } from "react-native";
import ShareIcon from "../../../../assets/share.png";
import Button from "../../../../components/buttons/LargeButton";
import ViewShot from "react-native-view-shot";
import { useNavigation } from "@react-navigation/native";
import * as Sharing from "expo-sharing";
import axios from "axios";
import { useRecoilValue } from "recoil";
import { gameIdState } from "../../../../recoil/recoil";
import style from "./style";
import API_URL from "../../../../../config";

const FirstResultPage = (props) => {
  const gameId = props.route.params?.gameId || useRecoilValue(gameIdState);
  const [apiData, setApiData] = useState(null);

  useEffect(() => {
    axios
      .get(`${API_URL}/api/game/result/${gameId}`)
      .then((response) => {
        setApiData(response.data);
      })
      .catch((error) => {});
  }, [gameId]);
  useEffect(() => {
    console.log("현재 상태의 값", apiData);
  }, [apiData]);
  const handleShare = async () => {
    try {
      const uri = await viewRef.current.capture({
        format: "jpeg",
        quality: 0.8,
        result: "tmpfile",
        snapshotContentContainer: true,
        backgroundColor: "#FFFFFF",
      });

      await Sharing.shareAsync(Platform.OS === "ios" ? `file://${uri}` : uri, {
        mimeType: "image/jpeg",
        dialogTitle: "결과 공유하기",
        UTI: "image/jpeg",
      });
    } catch (error) {
      console.error("캡처 또는 공유 중 에러:", error);
    }
  };

  const viewRef = useRef();
  const navigation = useNavigation();

  const handleFirstResultPage = () => {
    navigation.navigate("GameResult", {
      screen: "SecondResultPage",
      params: { gameId }
    });
  };
  

  if (!apiData) {
    return <Text>Loading...</Text>;
  }

  const assetsInfo = apiData.data.assetsInfo;
  const totalAssets = apiData.data.totalAssets;
  const userInfo = apiData.data.userInfo;
  return (
    <ScrollView style={style.container}>
      <TouchableOpacity style={style.shareButton} onPress={handleShare}>
        <Image source={ShareIcon} style={style.shareIcon} />
      </TouchableOpacity>

      <ViewShot ref={viewRef}>
        {/* 결과 분석표 */}
        <View style={[style.section, style.marginBottom]}>
          <Text style={{ fontSize: 26 }}>결과 분석표</Text>
        </View>

        {/* 사용자 정보 */}
        <View style={[style.section, style.userInfo, style.marginBottom]}>
          <Image source={{ uri: userInfo.profileImg }} style={style.profileImage} />
          <View>
            <Text style={[style.userName, { fontSize: 18 }]}>{`이름 : ${userInfo.name}`}</Text>
            <Text style={{ fontSize: 16 }}>{`총 자산: ${totalAssets}원`}</Text>
          </View>
        </View>

        {/* 테이블 */}
        <View style={[style.section, style.tableContainer, style.marginBottom]}>
          <View style={style.table}>
            <View style={[style.tableRow, style.tableHeader]}>
              <Text style={style.tableCell}>총 자산 항목</Text>
              <Text style={style.tableCell}>단위 (원)</Text>
              <Text style={style.tableCell}>변동치 (원)</Text>
            </View>
            {/* 여유자금 행 */}
            <View style={style.tableRow}>
              <Text style={style.tableCell}>여유자금</Text>
              <Text style={style.tableCell}>{assetsInfo.usableAsset}</Text>
              <Text style={style.tableCell}>-</Text>
            </View>
            {/* IRP 행 */}
            <View style={style.tableRow}>
              <Text style={style.tableCell}>IRP</Text>
              <Text style={style.tableCell}>{assetsInfo.irpasset}</Text>
              <Text style={style.tableCell}>-</Text>
            </View>
            {/* 적금 행 */}
            <View style={style.tableRow}>
              <Text style={style.tableCell}>적금</Text>
              <Text style={style.tableCell}>{assetsInfo.installmentSavingAsset}</Text>
              <Text style={style.tableCell}>-</Text>
            </View>
            {/* 주식 행 */}
            <View style={style.tableRow}>
              <Text style={style.tableCell}>주식</Text>
              <Text style={style.tableCell}>{assetsInfo.stockAsset}</Text>
              <Text style={[style.tableCell, assetsInfo.stockVariable > 0 ? style.positiveText : style.negativeText]}>
                {assetsInfo.stockVariable}
              </Text>
            </View>
            {/* 부동산 행 */}
            <View style={style.tableRow}>
              <Text style={style.tableCell}>부동산</Text>
              <Text style={style.tableCell}>{assetsInfo.realtyAsset}</Text>
              <Text style={[style.tableCell, assetsInfo.realtyVariable > 0 ? style.positiveText : style.negativeText]}>
                {assetsInfo.realtyVariable}
              </Text>
            </View>
            {/* 대출 행 */}
            <View style={style.tableRow}>
              <Text style={style.tableCell}>대출</Text>
              <Text style={style.tableCell}>{assetsInfo.loanAsset}</Text>
              <Text style={style.tableCell}>-</Text>
            </View>
          </View>
        </View>

        {/* 버튼 */}
        <View style={[style.section, style.marginBottom]}>
          <Button title="노후 생활비 분석 ➔" onPress={handleFirstResultPage} />
        </View>
      </ViewShot>
    </ScrollView>
  );
};

export default FirstResultPage;
