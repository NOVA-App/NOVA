import React, {useContext} from 'react';
import {createStackNavigator} from '@react-navigation/stack';

import {ChooseOptions} from './template/index';

const SelecOptionPageStack = createStackNavigator();

export default function SelectOptionPage() {
  return (
    <SelecOptionPageStack.Navigator>
        <SelecOptionPageStack.Screen name="SelectOptionPage" component={ChooseOptions} />
    </SelecOptionPageStack.Navigator>
     
  );
}
