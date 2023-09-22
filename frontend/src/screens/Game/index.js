import React from "react";
import { createStackNavigator } from "@react-navigation/stack";

import { GameMainPage, Banking } from "./template";

const GameStack = createStackNavigator();

export default function Game() {
  return (
    <GameStack.Navigator>
      <GameStack.Screen name="GameMainPage" component={GameMainPage} />
      <GameStack.Screen name="Banking" component={Banking} />
    </GameStack.Navigator>
  );
}