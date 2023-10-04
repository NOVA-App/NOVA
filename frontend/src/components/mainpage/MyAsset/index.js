import React from "react";
import { Dimensions, View, Text } from "react-native";
import UpSemiCircle from "../UpSemiCircle";
import DownSemiCircle from "../DownSemiCircle";
import * as S from "./style";

const { width, height } = Dimensions.get("window");

// 버튼 이름, 눌릴 때 함수, 배경 넣기
const MyAsset = (props) => {
  return (
    <S.Container style={{ marginTop: "3%" }}>
      <View>
        <UpSemiCircle
          title="주식"
          amount={[props.asset.stocksAsset].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')}
          bgColor="#4E5EEA"
        />
        <DownSemiCircle
          title="부동산"
          amount={[props.asset.realtyAsset].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')}
          bgColor="#34B658"
        />
      </View>
      <View>
        <UpSemiCircle
          title="금융자산"
          amount={[props.asset.savingAsset].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')}
          bgColor="#E32A56"
        />
        <DownSemiCircle
          title="대출"
          amount={[props.asset.loanAsset].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')}
          bgColor="#F4EB1D"
        />
      </View>
    </S.Container>
  );
};

export default MyAsset;
