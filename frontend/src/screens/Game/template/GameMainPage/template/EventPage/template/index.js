import React from 'react';
import MarriagePage from "./MarriagePage"
import ChildPage from "./ChildPage"
import {createStackNavigator} from '@react-navigation/stack';

const EventPageStack = createStackNavigator();

export function EventPage() {
  return (
    <EventPageStack.Navigator>
      <EventPageStack.Screen name="MarriagePage" component={MarriagePage} options={{headerShown: false}}/>
      <EventPageStack.Screen name="ChildPage" component={ChildPage} options={{headerShown: false}}/>
    </EventPageStack.Navigator>
  );
}
