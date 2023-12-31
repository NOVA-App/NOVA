import React from "react";
import { Image } from "react-native";
import { createStackNavigator } from "@react-navigation/stack";
import { createBottomTabNavigator } from "@react-navigation/bottom-tabs";
import { GameMainPage, Banking, RealEstatePage, NewsPage, StockPage } from "./template";
import Budget from "../../components/budget";

const GameStack = createStackNavigator();
const Tab = createBottomTabNavigator();

function BottomNav() {
  return (
    <Tab.Navigator initialRouteName="GameMainPage">
      <Tab.Screen
        name="NewsPage"
        component={NewsPage}
        options={{
          tabBarLabel: "뉴스",
          headerShown: false,
          tabBarIcon: ({ focused, color, size }) => (
            <Image
              source={
                focused
                  ? require("../../assets/BottomNav/News.png")
                  : require("../../assets/BottomNav/News.png")
              }
              style={{ width: 30, height: 30 }}
            />
          ),
        }}
      />
      <Tab.Screen
        name="Banking"
        component={Banking}
        options={{
          tabBarLabel: "뱅킹",
          headerShown: false,
          tabBarIcon: ({ focused, color, size }) => (
            <Image
              source={
                focused
                  ? require("../../assets/BottomNav/Finance.png")
                  : require("../../assets/BottomNav/Finance.png")
              }
              style={{ width: size, height: size }}
            />
          ),
        }}
      />
      <Tab.Screen
        name="GameMainPage"
        component={GameMainPage}
        options={{
          tabBarLabel: "Home",
          headerShown: false,
          tabBarIcon: ({ focused, color, size }) => (
            <Image
              source={
                focused
                  ? require("../../assets/BottomNav/Home.png")
                  : require("../../assets/BottomNav/Home.png")
              }
              style={{ width: 30, height: 30 }}
            />
          ),
        }}
      />
      <Tab.Screen
        name="RealEstatePage"
        component={RealEstatePage}
        options={{
          tabBarLabel: "부동산",
          headerShown: false,
          tabBarIcon: ({ focused, color, size }) => (
            <Image
              source={
                focused
                  ? require("../../assets/BottomNav/Property.png")
                  : require("../../assets/BottomNav/Property.png")
              }
              style={{ width: 30, height: 30 }}
            />
          ),
        }}
      />
      <Tab.Screen
        name="StockPage"
        component={StockPage}
        options={{
          tabBarLabel: "주식",
          headerShown: false,
          tabBarIcon: ({ focused, color, size }) => (
            <Image
              source={
                focused
                  ? require("../../assets/BottomNav/Stock.png")
                  : require("../../assets/BottomNav/Stock.png")
              }
              style={{ width: 30, height: 30 }}
            />
          ),
        }}
      />
    </Tab.Navigator>
  );
}
export default function Game() {
  return (
    <>
    <Budget/>
    <GameStack.Navigator initialRouteName="BottomNav">
      <GameStack.Screen name="BottomNav" component={BottomNav} options={{ headerShown: false }}/>
    </GameStack.Navigator>
    </>
  );
}
