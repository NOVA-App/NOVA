import React from "react";
import { createStackNavigator } from "@react-navigation/stack";

import { FirstResultPage } from "./template";

const GameResultStack = createStackNavigator();

export default function GameResult() {
  return (
    <GameResultStack.Navigator initialRouteName="FirstResultPage">
      <GameResultStack.Screen name="FirstResultPage" component={FirstResultPage}  />
    </GameResultStack.Navigator>
  );
}
