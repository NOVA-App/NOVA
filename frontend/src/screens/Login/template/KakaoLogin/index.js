import React from "react";
import { View, StyleSheet } from "react-native";
import { WebView } from 'react-native-webview';
import axios from 'axios';
import AsyncStorage from '@react-native-async-storage/async-storage';
import { useNavigation } from "@react-navigation/native";

const REST_API_KEY = 'ad1df559dc905b03ec9d59ad4afbe212';
const REDIRECT_URI = 'http://nohoo.site/api/oauth/kakao/callback';
const INJECTED_JAVASCRIPT = `window.ReactNativeWebView.postMessage('message from webView')`;

const KaKaoLogin = () => {

  const navigation = useNavigation();

  function KakaoLoginWebView (data) {
    const exp = "code=";
    var condition = data.indexOf(exp);    
    if (condition != -1) {
      var authorize_code = data.substring(condition + exp.length);
      console.log('여기는 인가코드');
      console.log(authorize_code);
      requestToken(authorize_code);
    };
  }

  const requestToken = async (authorize_code) => {
    console.log('토큰 요청 시작');
    var AccessToken = "none";
    
    try {
      const response = await axios.post(
        "https://kauth.kakao.com/oauth/token",
        null,
        {
          headers: {
            "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
          },
          data: {
            grant_type: "authorization_code",
            client_id: REST_API_KEY,
            redirect_uri: REDIRECT_URI,
            code: authorize_code,
          },
        }
      );
  
      AccessToken = response.data.access_token;
      console.log('여기는 토큰');
      console.log(AccessToken);
      // requestUserInfo(AccessToken);
      storeData(AccessToken);
  
      // 토큰 발급에 성공한 경우 "Main"으로 이동
      navigation.navigate("Main", { screen: "StartingGame" });
    } catch (error) {
      console.log('토큰 발급 오류:', error);
      // 오류 처리 또는 사용자에게 오류 메시지 표시
      // 토큰 발급에 실패한 경우 "Signin"으로 이동해야 하는데 일단은 Main으로 이동
      navigation.navigate("Main", { screen: "StartingGame" });
      // navigation.navigate("Signin", { screen: "Signin" });
    }
  };
  

  // function requestUserInfo(AccessToken)  {
  //   axios ({
  //     method: 'GET',
  //     url: 'https://kapi.kakao.com/v2/user/me',
  //     headers: {
  //       Authorization : `Bearer ${AccessToken}`
  //     },
  //   }).then((response) => {
  //     var user_emil = response.data.kakao_account.email;
  //     var user_range = response.data.kakao_account.age_range;
  //     var user_gender = response.data.kakao_account.gender;
  //     console.log("user_emil", user_emil);
  //     console.log("user_range", user_range);
  //     console.log("user_gender", user_gender);
  //   }).catch(function (error) {
  //     console.log('error', error);
  //   })
  //   return;
  // }

  const storeData = async (returnValue) => {
    try {
      await AsyncStorage.setItem('userAccessToken', returnValue);
    } catch (error) {
    }
  }

  return (
    <View style={Styles.container}>      
      <WebView
        style={{ flex: 1 }}
        originWhitelist={['*']}
        scalesPageToFit={false}
        source={{
          uri: `https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}`,
        }}
        injectedJavaScript={INJECTED_JAVASCRIPT}
        javaScriptEnabled
        onMessage={event => { KakaoLoginWebView(event.nativeEvent["url"]); }}
      />
    </View>
  )
}

export default KaKaoLogin;

const Styles = StyleSheet.create({
  container: {
    flex: 1,
    marginTop: 24,
    backgroundColor: '#fff',
  },    
});