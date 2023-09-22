import React from "react";
import { createStackNavigator } from "@react-navigation/stack";

import { StartingGame } from "./template";

const MainStack = createStackNavigator();

export default function Main() {
  return (
    <MainStack.Navigator>
      <MainStack.Screen name="StartingGame" component={StartingGame} />
    </MainStack.Navigator>
  );
}
