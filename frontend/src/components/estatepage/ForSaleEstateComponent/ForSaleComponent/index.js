import React from "react";
import { Dimensions, View, Text, ScrollView } from "react-native";
import * as S from "./style";
import HouseImg from "../../../../assets/House.png";
import Button from "../../../buttons/SmallButton";
const { height } = Dimensions.get("window");

const ForSaleDetail = (props) => {
  const ID = props.realtyId
  // const [realtyDetailData, setRealtyDetailData] = useState([]);

  useEffect(() => {
    axios
      .get(`${API_URL}/api/realty/1/${ID}`) // 게임아이디 받아와서 주기
      .then((response) => {
        setRealtyData(response.data.data);
      })
      .catch((error) => {
        console.error("데이터를 가져오는 동안 오류 발생: ", error);
      });
  }, []);

  return (
    <View style={{ flex: 1 }}>
      <S.Container style={{ flex: 8.5, minWidth: "80%" }}>
        <View
          style={{
            flex: 0.2,
          }}
        ></View>
        <View style={{ flex: 8, alignItems: "center" }}>

        {realtyData.map((realtyItem, index) => (
        <View key={index}>
          <S.ImgContainer source={HouseImg}></S.ImgContainer>
          <View style={{ marginTop: "5%" }}></View>
          <S.InfoText>
          {realtyItem.realtyName} ({"  "}
            <S.InfoText style={{ color: "#D90452" }}>+20%</S.InfoText> )
          </S.InfoText>
          <S.InfoText>{`지역    `}{realtyItem.region}</S.InfoText>
          <S.InfoText>{`예상 월세 수익    `}{realtyItem.predictedRentIncome}</S.InfoText>
          <S.InfoText>
            {`총금액          `}
            {realtyItem.totalPrice}
          </S.InfoText>
          <S.InfoText>
            {`매물가격          `}
            {realtyItem.evaluationAmount}
          </S.InfoText>
          <S.InfoText>{`취득세        `}{realtyItem.acquistionTax}</S.InfoText>
          <S.InfoText>
            {`모자란 금액     `}
            <S.InfoText style={{ color: "#D90452" }}>-100</S.InfoText>
          </S.InfoText>
        </View>
      ))}


        </View>
        <View
          style={{
            marginBottom: "5%",
            marginRight: "5%",
            alignItems: "flex-end",
          }}
        >
          <Button title="매수하기" bgColor="#0046FF" />
        </View>
      </S.Container>
    </View>
  );
};

export default ForSaleDetail;
