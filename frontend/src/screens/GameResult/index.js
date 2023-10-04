import React from "react";
import { createStackNavigator } from "@react-navigation/stack";

import { FirstResultPage, SecondResultPage } from "./template";

const GameResultStack = createStackNavigator();

export default function GameResult() {
  return (
    <GameResultStack.Navigator initialRouteName="FirstResultPage">
      <GameResultStack.Screen name="FirstResultPage" component={FirstResultPage}  />
      <GameResultStack.Screen name="SecondResultPage" component={SecondResultPage}  />
    </GameResultStack.Navigator>
  );
}
