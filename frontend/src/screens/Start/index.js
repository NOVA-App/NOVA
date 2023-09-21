import React, {useContext} from 'react';
import {createStackNavigator} from '@react-navigation/stack';

import {LoginPage} from './template/index';

const StartStack = createStackNavigator();

export default function Start() {
  return (
    <StartStack.Navigator>
        <StartStack.Screen name="LoginPage" component={LoginPage} />
    </StartStack.Navigator>
     
  );
}
