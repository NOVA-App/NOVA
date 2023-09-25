import React from "react";
import { createStackNavigator } from "@react-navigation/stack";
import { Login, Main, Game } from "../screens";
import { Account } from "../screens/Game/template/Account";
import FinancialProduct from "../screens/Game/template/FinancialProduct";

const RootStack = createStackNavigator();

const RootStackNavigator = () => {
  return (
    <RootStack.Navigator headerMode="none">
      <RootStack.Screen name="Account" component={Account} />
      <RootStack.Screen name="Game" component={Game} />
      <RootStack.Screen name="FinancialProduct" component={FinancialProduct} />
      <RootStack.Screen name="Login" component={Login} />
      <RootStack.Screen name="Main" component={Main} />
    </RootStack.Navigator>
  );
};

export default RootStackNavigator;
