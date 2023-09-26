import React from "react";
import { createStackNavigator } from "@react-navigation/stack";
import { Login, Main, Game } from "../screens";
import FinancialProduct from "../screens/Game/template/Banking/template/FinancialProduct";
import Account from "../screens/Game/template/Banking/template/Account/index"

const RootStack = createStackNavigator();

const RootStackNavigator = () => {
  return (
    <RootStack.Navigator headerMode="none">
      <RootStack.Screen name="FinancialProduct" component={FinancialProduct} />
      <RootStack.Screen name="Account" component={Account} />
      <RootStack.Screen name="Game" component={Game} />
      <RootStack.Screen name="Main" component={Main} />
      <RootStack.Screen name="Login" component={Login} />
    </RootStack.Navigator>
  );
};

export default RootStackNavigator;
