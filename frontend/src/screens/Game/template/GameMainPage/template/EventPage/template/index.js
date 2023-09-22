import React from 'react';
import MarriagePage from "./MarriagePage"
import ChildPage from "./ChildPage"
import {createStackNavigator} from '@react-navigation/stack';

const EventPageStack = createStackNavigator();

export function EventPage() {
  return (
    <EventPageStack.Navigator
      initialRouteName="ChattingMain">
      <EventPageStack.Screen name="MarriagePage" component={MarriagePage} />
      <EventPageStack.Screen name="ChildPage" component={ChildPage} />
    </EventPageStack.Navigator>
  );
}
