import React from "react";
import { createStackNavigator } from "@react-navigation/stack";

import { StartingGame, MyPage, RankingPage } from "./template";

const MainStack = createStackNavigator();

export default function Main() {
  return (
    <MainStack.Navigator>
      <MainStack.Screen name="StartingGame" component={StartingGame}  />
      <MainStack.Screen name="MyPage" component={MyPage} />
      <MainStack.Screen name="RankingPage" component={RankingPage} />
    </MainStack.Navigator>
  );
}
