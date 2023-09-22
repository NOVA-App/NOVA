import React from "react";
import LoginPage from "../screens/loginpage/index";
import StartGame from "../screens/selectoptionpage/index";
import GameStart from "../screens/GameStartPage";
import ChildPage from "../screens/ChildPage";
import MarriagePage from "../screens/MarriagePage";
import MainPage from "../screens/MainPage";
import MenuPage from "../screens/MenuPage";
import MyRealEstatePage from "../screens/RealEstatePage/MyRealEstatePage";
import { createStackNavigator } from "@react-navigation/stack";

const Stack = createStackNavigator();

const StackNavigator = () => {
  return (
    <Stack.Navigator>
      <Stack.Screen name="MyRealEstatePage" component={MyRealEstatePage} />
      <Stack.Screen name="MarriagePage" component={MarriagePage} />
      <Stack.Screen name="ChildPage" component={ChildPage} />
      <Stack.Screen name="LoginPage" component={LoginPage} />
      <Stack.Screen name="StartGame" component={StartGame} />
      <Stack.Screen name="GameStart" component={GameStart} />
      <Stack.Screen name="MenuPage" component={MenuPage} />
      <Stack.Screen name="MainPage" component={MainPage} />
    </Stack.Navigator>
  );
};

export default StackNavigator;
