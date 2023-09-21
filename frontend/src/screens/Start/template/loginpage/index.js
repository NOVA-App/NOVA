import React, {useContext} from 'react';
import {createStackNavigator} from '@react-navigation/stack';

import {KakaoLoginPage} from './template/index';

const LoginPageStack = createStackNavigator();

export default function LoginPage() {
  return (
    <LoginPageStack.Navigator>
        <LoginPageStack.Screen name="KakaoLoginPage" component={KakaoLoginPage} />
    </LoginPageStack.Navigator>
     
  );
}
