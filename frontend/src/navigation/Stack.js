import React from "react";
import { createStackNavigator } from "@react-navigation/stack";
import { Login, Main, Game } from "../screens";

const RootStack = createStackNavigator();

const RootStackNavigator = () => {
  return (
    <RootStack.Navigator headerMode="none">
      <RootStack.Screen name="Game" component={Game} />
      <RootStack.Screen name="Main" component={Main} />
      <RootStack.Screen name="Login" component={Login} />
    </RootStack.Navigator>
  );
};

export default RootStackNavigator;
