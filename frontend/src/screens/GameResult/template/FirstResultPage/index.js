import React, { useRef } from "react";
import { View, Text, Image, StyleSheet, ScrollView, TouchableOpacity } from "react-native";
import House from "../../../../assets/House.png";
import ShareIcon from "../../../../assets/share.png";
import Button from "../../../../components/buttons/SmallButton";
import ViewShot from "react-native-view-shot";
import * as Sharing from "expo-sharing";

const FirstResultPage = (props) => {
  const data = [
    { category: "여유자금", amount: "15,000,000", change: "-" },
    { category: "IRP", amount: "20,000", change: "-" },
    { category: "적금", amount: "20,000", change: "+20,000" },
    { category: "주식", amount: "20,000", change: "-20,000" },
    { category: "부동산", amount: "20,000", change: "+20,000" },
    { category: "대출", amount: "20,000", change: "-" },
  ];

  const handleShare = async () => {
    try {
      // 캡처
      const uri = await viewRef.current.capture({
        format: "jpeg", // 이미지 형식을 JPEG로 설정
        quality: 0.8,
        result: "tmpfile", // 임시 파일로 저장
        snapshotContentContainer: true, // 컨테이너 전체를 캡처하지 않도록 설정
        backgroundColor: "#FFFFFF", // 배경색을 흰색으로 설정);
      });

      // 이미지 공유
      await Sharing.shareAsync(Platform.OS === "ios" ? `file://${uri}` : uri, {
        mimeType: "image/jpeg", // 이미지 형식을 JPEG로 설정
        dialogTitle: "공유하기",
        UTI: "image/jpeg",
      });
    } catch (error) {
      console.error("캡처 및 공유 중 에러:", error);
    }
  };

  const viewRef = useRef();

  return (
    <ScrollView style={styles.container}>
      {/* Share 버튼 */}
      <TouchableOpacity style={styles.shareButton} onPress={handleShare}>
        <Image source={ShareIcon} style={styles.shareIcon} />
      </TouchableOpacity>

      {/* 결과 분석표 */}
      <ViewShot ref={viewRef}>
        <View style={[styles.section, styles.marginBottom]}>
          {/* 결과 분석표 구현 */}
          <Text style={{ fontSize: 26 }}>결과 분석표</Text>
        </View>

        {/* 사용자 정보 */}
        <View style={[styles.section, styles.userInfo, styles.marginBottom]}>
          {/* 사용자 프로필 사진 */}
          <Image source={House} style={styles.profileImage} />
          {/* 이름과 총 자산 */}
          <View>
            <Text style={[styles.userName, { fontSize: 18 }]}>이름 : 신석철</Text>
            <Text style={{ fontSize: 16 }}>총 자산: 11,000,000,000원</Text>
          </View>
        </View>

        {/* 그래프 */}
        <View style={[styles.section, styles.marginBottom]}>{/* 그래프 이미지 */}</View>

        {/* 테이블 */}
        <View style={[styles.section, styles.tableContainer, styles.marginBottom]}>
          <View style={styles.table}>
            {/* 윗줄에만 다른 색상을 적용 */}
            <View style={[styles.tableRow, styles.tableHeader]}>
              <Text style={styles.tableCell}>총 자산 항목</Text>
              <Text style={styles.tableCell}>단위 (원)</Text>
              <Text style={styles.tableCell}>변동치 (원)</Text>
            </View>
            {data.map((item, index) => (
              <View key={index} style={styles.tableRow}>
                <Text style={styles.tableCell}>{item.category}</Text>
                <Text style={styles.tableCell}>{item.amount}</Text>
                <Text style={styles.tableCell}>{item.change}</Text>
              </View>
            ))}
          </View>
        </View>

        {/* 버튼 */}
        <View style={[styles.section, styles.marginBottom]}>
          <Button title="맨 아래 버튼" onPress={() => console.log("맨 아래 버튼 클릭")} />
        </View>
      </ViewShot>
    </ScrollView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "white",
    paddingHorizontal: 20, // 가로 여백 추가
    width: "100%", // 가로 길이 100%
  },
  section: {
    justifyContent: "center",
    alignItems: "center",
    flex: 1,
  },
  marginBottom: {
    marginBottom: 10,
  },
  userInfo: {
    flexDirection: "column", // 수정된 부분
    alignItems: "center",
  },
  userName: {
    marginBottom: 5, // 수정된 부분
  },
  profileImage: {
    width: 50,
    height: 50,
    borderRadius: 25,
    marginBottom: 10, // 이미지 아래 간격 추가
  },
  tableContainer: {
    flex: 3,
    justifyContent: "center", // 가로 방향 중앙 정렬
    alignItems: "center",
  },
  table: {
    flexDirection: "column",
    borderWidth: 1,
    borderColor: "#000",
    width: "100%", // 테이블의 가로 너비 100%
    borderRadius: 5,
    overflow: "hidden",
  },
  tableHeader: {
    backgroundColor: "#7DE4C0", // 다른 색상으로 변경 가능
  },
  tableRow: {
    flexDirection: "row",
    justifyContent: "space-between",
    borderBottomWidth: 1,
    borderBottomColor: "#000",
    paddingVertical: 8,
  },
  tableCell: {
    flex: 1,
    padding: 10,
    textAlign: "center",
  },
  shareButton: {
    position: "absolute",
    top: 10,
    right: 10,
    zIndex: 1,
  },
  shareIcon: {
    width: 30,
    height: 30,
  },
});

export default FirstResultPage;
