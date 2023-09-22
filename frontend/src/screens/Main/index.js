import React, { useContext } from "react";
import { createStackNavigator } from "@react-navigation/stack";

import SelectOptionPage from "./template/selectoptionpage";

const MainStack = createStackNavigator();

export default function Main() {
  return (
    <MainStack.Navigator initialRouteName="SelectOptionPage">
      <MainStack.Screen name="SelectOptionPage" component={SelectOptionPage} />
    </MainStack.Navigator>
  );
}
