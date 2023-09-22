import React from "react";
import {MainComponents} from "./MainComponents";
import { createStackNavigator } from "@react-navigation/stack";

const MainPageStack = createStackNavigator();

export function MainPage() {
  return (
    <MainPageStack.Navigator
      initialRouteName="ChattingMain">
      <MainPageStack.Screen name="MainComponents" component={MainComponents}/>
    </MainPageStack.Navigator>
    );
};