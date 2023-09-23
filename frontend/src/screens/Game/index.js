import React from "react";
import { Image } from 'react-native';
import { createStackNavigator } from "@react-navigation/stack";
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { GameMainPage, Banking } from "./template";

const GameStack = createStackNavigator();
const Tab = createBottomTabNavigator();

function BottomNav() {
  return(
    <Tab.Navigator>
      <Tab.Screen name="Banking" component={Banking} options={{
        tabBarLabel: '뱅킹',
        tabBarIcon: ({ focused, color, size }) => (
          <Image
          source={focused ? require('../../assets/BottomNav/Finance.png') : require('../../assets/BottomNav/Finance.png')}
          style={{ width: size, height: size }}
          />
          ),
        }}/>
        <Tab.Screen name="GameMainPage" component={GameMainPage} options={{
      tabBarLabel: 'Home',
      tabBarIcon: ({ focused, color, size }) => (
        <Image
          source={focused ? require('../../assets/BottomNav/Home.png') : require('../../assets/BottomNav/Home.png')}
          style={{ width: 30, height: 30 }}
        />
      ),
    }}/>
      {/* <Tab.Screen name="NewsPage" component={NewsPage}/>
      <Tab.Screen name="PropertyPage" component={PropertyPage}/>
      <Tab.Screen name="StockPage" component={StockPage}/> */}
    </Tab.Navigator>
  )
}
export default function Game() {
  return (
    <GameStack.Navigator initialRouteName="BottomNav">
      <GameStack.Screen name="BottomNav" component={BottomNav} />
    </GameStack.Navigator>
  );
}
