import React from 'react';
import {createStackNavigator} from '@react-navigation/stack';

import { MenuPage, GameStartPage } from './template';

const StartingGameStack = createStackNavigator();

export default function StartingGame() {
  return (
    <StartingGameStack.Navigator>
        <StartingGameStack.Screen name="MenuPage" component={MenuPage} options={{ headerShown: false }}/>
        <StartingGameStack.Screen name="GameStartPage" component={GameStartPage} options={{ headerShown: false }}/>
    </StartingGameStack.Navigator>
     
  );
}
