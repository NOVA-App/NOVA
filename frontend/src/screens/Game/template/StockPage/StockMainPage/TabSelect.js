import React from "react";
import { View } from "react-native";
import MyStock from "../../../../../components/stockpage/MyStockComponent";
import StockTrade from "../../../../../components/stockpage/StockTradeComponent";

const TabSelect = ({ selectedTab }) => {
  return (
    <View>
      {selectedTab == 0 && <MyStock />}
      {selectedTab == 1 && <StockTrade />}
    </View>
  );
};
export default TabSelect;
