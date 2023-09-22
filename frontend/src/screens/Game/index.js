import React from "react";
import { createStackNavigator } from "@react-navigation/stack";
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { GameMainPage, Banking } from "./template";

const GameStack = createStackNavigator();
const Tab = createBottomTabNavigator();

function BottomNav() {
  return(
    <Tab.Navigator>
      <Tab.Screen name="Banking" component={Banking}/>
      {/* <Tab.Screen name="NewsPage" component={NewsPage}/>
      <Tab.Screen name="PropertyPage" component={PropertyPage}/>
      <Tab.Screen name="StockPage" component={StockPage}/> */}
    </Tab.Navigator>
  )
}
export default function Game() {
  return (
    <GameStack.Navigator>
      <GameStack.Screen name="GameMainPage" component={GameMainPage} />
      <GameStack.Screen name="Banking" component={Banking} />
      {/* <BottomNav/> */}
    </GameStack.Navigator>
  );
}
