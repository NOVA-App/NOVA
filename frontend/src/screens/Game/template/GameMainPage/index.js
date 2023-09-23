import React from 'react';
import {createStackNavigator} from '@react-navigation/stack';
import { EventPage } from './template';
import { MainPage } from './template';

const GameMainPageStack = createStackNavigator();

export function GameMainPage() {
    return (
      <GameMainPageStack.Navigator initialRouteName="MainPage">
        <GameMainPageStack.Screen name="MainPage" component={MainPage} options={{ headerShown: false }}/>
        <GameMainPageStack.Screen name="EventPage" component={EventPage} options={{ headerShown: false }}/>
      </GameMainPageStack.Navigator>
    );
  }
  