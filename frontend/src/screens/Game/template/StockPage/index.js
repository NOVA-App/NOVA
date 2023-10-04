import React from "react";
import { createStackNavigator } from "@react-navigation/stack";
import { StockMainPage } from "./StockMainPage";
import StockDetailPage from "./StockDetailPage";

const Stack = createStackNavigator();

const StockPage = () => {
  return (
    <Stack.Navigator initialRouteName="StockMainPage" >
      <Stack.Screen
        name="StockMainPage"
        component={StockMainPage}
        options={{ title: "Stock Main Page", headerShown: false }}
      />
      <Stack.Screen
        name="StockDetailPage"
        component={StockDetailPage}
        options={{ title: "Stock Detail Page", headerShown: false }}
      />
    </Stack.Navigator>
  );
};

export default StockPage;
