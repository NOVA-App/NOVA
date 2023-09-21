import React from 'react';
import LoginPage from '../screens/loginpage/index';
import StartGame from '../screens/selectoptionpage/index';
import GameStart from '../screens/GameStartPage';
import ChildPage from '../screens/ChildPage';
import MarriagePage from '../screens/MarriagePage';
import { createStackNavigator } from '@react-navigation/stack';

const Stack = createStackNavigator();

const StackNavigator = () => {
  return (
    <Stack.Navigator>
      <Stack.Screen name="MarriagePage" component={MarriagePage} />
      <Stack.Screen name="LoginPage" component={LoginPage} />
      <Stack.Screen name="StartGame" component={StartGame} />
      <Stack.Screen name="GameStart" component={GameStart} />
      <Stack.Screen name="ChildPage" component={ChildPage} />
    </Stack.Navigator>
  );
};

export default StackNavigator;
