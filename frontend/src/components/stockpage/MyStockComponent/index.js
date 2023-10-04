import React, { useEffect, useState } from "react";
import { Dimensions, View, Text, ScrollView } from "react-native";
import StockCard from "./StockCard";
import * as S from "./style";
import axios from "axios";
import { gameIdState, gameDataState } from "../../../recoil/recoil";
import { useRecoilState } from "recoil";
import API_URL from "../../../../config";
import { annualModalState } from "../../../recoil/recoil";

const { height } = Dimensions.get("window");

const MyStock = () => {
  const [gameId] = useRecoilState(gameIdState);
  const [myData, setMyData] = useState({});
  const [refresh] = useRecoilState(annualModalState);
  const [gameData] = useRecoilState(gameDataState);
  const cost = gameData.annualAssets.usableAsset;
  const [myStock, setMyStock] = useState([]);

  useEffect(() => {
    axios
      .get(`${API_URL}/api/stock/mine/${gameId}`)
      .then((response) => {
        setMyData(response.data.data);
        if (response.data.data && response.data.data.myStocks) {
          setMyStock(response.data.data.myStocks);
        }
      })
      .catch((error) => {
        console.error("주식 데이터 가져오기 오류:", error);
      });
  }, [cost]);

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
            <Text style={{ fontSize: 20, color: "white" }}>보유 주식</Text>
          </S.TagContainer>
        </View>
        <View style={{ flex: 8 }}>
          <View>
            <S.TotalAssetContainer>
              <S.rowContainer>
                <S.CenterView>
                  <S.MiddleText>{`매입금액`}</S.MiddleText>
                  <S.MiddleText>
                    {myData ? [myData.investAmounts].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',') : 0}
                  </S.MiddleText>
                </S.CenterView>
                <S.CenterView>
                  <S.MiddleText>{`평가 손익`}</S.MiddleText>
                  <S.MiddleText>
                    {myData ? [myData.depreciation].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',') : 0}
                  </S.MiddleText>
                </S.CenterView>
              </S.rowContainer>
              <S.rowContainer>
                <S.CenterView>
                  <S.MiddleText>{`평가금액`}</S.MiddleText>
                  <S.MiddleText>
                    {myData ? [myData.evaluationAmounts].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',') : 0}
                  </S.MiddleText>
                </S.CenterView>
                <S.CenterView>
                  <S.MiddleText>{`수익률`}</S.MiddleText>
                  <S.MiddleText>
                    {" "}
                    {myData
                      ? myData.depreciationPercent > 0
                        ? "+" + myData.depreciationPercent
                        : myData.depreciationPercent
                      : 0}
                    %
                  </S.MiddleText>
                </S.CenterView>
              </S.rowContainer>
            </S.TotalAssetContainer>
          </View>
          <View style={{ marginTop: "5%" }}>
            {myStock.length > 0 ? (
              <ScrollView>
                {myStock.map((stock, index) => (
                  <StockCard key={index} stock={stock} height={height} />
                ))}
              </ScrollView>
            ) : (
              <Text></Text>
            )}
          </View>
        </View>
      </S.Container>
    </View>
  );
};

export default MyStock;
