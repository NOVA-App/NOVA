import Signin from "./template/Signin"
import KaKaoLogin from "./template/KakaoLogin";
import { createStackNavigator } from "@react-navigation/stack"

const LoginStack = createStackNavigator();

export default function Login() {
  return ( 
  <LoginStack.Navigator initialRouteName="Signin">
    <LoginStack.Screen name="Signin" component={Signin}/>
    <LoginStack.Screen name="KakaoLogin" component={KaKaoLogin}/>
  </LoginStack.Navigator>
)
}

