import React from "react";
import { createStackNavigator } from "@react-navigation/stack";
import { Start, Main } from '../screens'

const RootStack = createStackNavigator();

const RootStackNavigator = () => {
  return (
    <RootStack.Navigator headerMode="none">
      <RootStack.Screen name="Start" component={Start}/>
      <RootStack.Screen name="Main" component={Main}/>
    </RootStack.Navigator>
  );
};

export default RootStackNavigator;
