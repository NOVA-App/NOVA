import React, { useContext } from "react";
import { createStackNavigator } from "@react-navigation/stack";

import { SelectOptionPage } from "./template/selectoptionpage/template/index";

const MainStack = createStackNavigator();

export default function Start() {
  return (
    <MainStack.Navigator>
      <StartStack.Screen name="SelectOptionPage" component={SelectOptionPage} />
    </MainStack.Navigator>
  );
}
