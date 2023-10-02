import React, { useState, useEffect } from "react";
import { Dimensions, View, Text, ScrollView } from "react-native";
import HouseCard from "./HouseCard";
import * as S from "./style";
import axios from "axios";
import API_URL from "../../../../config";

const { height } = Dimensions.get("window");

const LoanEstate = () => {
  const [loanData, setLoanData] = useState([]);

  useEffect(() => {
    axios
      .get(API_URL + "/api/realty/loan/1") // 게임아이디 받아와서 주기
      .then((response) => {
        setLoanData(response.data.data);
        console.log(response.data.data)
      })
      .catch((error) => {
        console.error("데이터를 가져오는 동안 오류 발생: ", error);
      });
  }, []);

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
            <Text style={{ fontSize: 20, color: "white" }}>대출목록</Text>
          </S.TagContainer>
        </View>
        <View style={{ flex: 8 }}>
          <View style={{ marginTop: "5%" }}>
            <ScrollView>
              {loanData.map((loanItem, index) => (
                <HouseCard
                  key={index}
                  realtyId={loanItem.realtyId}
                  loanId={loanItem.loanId}
                  realtyName={loanItem.realtyName}
                  realtyPrice={loanItem.realtyPrice}
                  principal={loanItem.principal}
                />
              ))}

              <HouseCard height={height} />
            </ScrollView>
          </View>
        </View>
      </S.Container>
    </View>
  );
};

export default LoanEstate;
