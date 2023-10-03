import React from "react";
import { Text, View, Image } from "react-native";
import Logo from "../../assets/nova_logo.png";
import styled from "styled-components/native";
import { gameDataState } from "../../recoil/recoil";
import { useRecoilState } from "recoil";

const Budget = () => {
  const [data] = useRecoilState(gameDataState);
  console.log(data);
  return (
    <StyledUpper>
      <View style={{ flex: 1, alignItems: "flex-start" }}>
        <Image
          style={{ resizeMode: "contain", width: "100%", marginLeft: 5 }}
          source={Logo}
        />
      </View>
      <View style={{ alignItems: "center", flex: 1, justifyContent: "center" }}>
        <Text style={{ fontSize: 24, fontWeight: "bold" }}>여유자금</Text>
        <Text style={{ fontSize: 20, color: "#F5B700" }}>
          {data.annualAssets.usableAsset}원
        </Text>
      </View>
      <View
        style={{ flex: 1, alignItems: "flex-end", justifyContent: "center" }}
      >
        <Text style={{ fontSize: 17 }}>메뉴</Text>
      </View>
    </StyledUpper>
  );
};

export default Budget;

// const styles = StyleSheet.create({
//   upper:{
//     flex: 1,
//     flexDirection: "row",
//     // justifyContent: 'center',
//     backgroundColor: 'white',
//     padding: 10,
//     // width: windowWidth,
//   },
// });

const StyledUpper = styled.View`
  flex-direction: row;
  background-color: white;
  padding: 10px;
  align-items: center;
  justify-content: space-between;
  height: 100px; /* 원하는 높이로 조절 */
`;
