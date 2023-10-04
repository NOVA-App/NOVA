import React, { useState } from "react";
import { View, StyleSheet } from "react-native";
import { ActivityIndicator } from "react-native";
import { WebView } from "react-native-webview";
import axios from "axios";
import { useNavigation } from "@react-navigation/native";
import { useRecoilState } from "recoil";
import { accessTokenState, refreshTokenState } from "../../../../recoil/recoil"

const REST_API_KEY = "9338dac6db9d9162c95333adbcb97200";
const REDIRECT_URI = "https://nohoo.site/api/oauth/kakao/callback";
const INJECTED_JAVASCRIPT = `window.ReactNativeWebView.postMessage('message from webView')`;

const KaKaoLogin = () => {
  const navigation = useNavigation();
  const [accessToken, setAccessToken] = useRecoilState(accessTokenState);
  const [refreshToken, setRefreshToken] = useRecoilState(refreshTokenState);

  function KakaoLoginWebView(data) {
    const exp = "code=";
    var condition = data.indexOf(exp);
    console.log("data " + data);
    console.log("condition " + condition);

    if (condition != -1) {
      var authorize_code = data.substring(condition + exp.length);
      console.log("여기는 인가코드");
      console.log(authorize_code);
      requestToken(authorize_code);
    }
  }

  const requestToken = async (authorize_code) => {
    console.log("토큰 요청 시작");
    var AccessToken = "none";
    var RefreshToken = "none";

    try {
      const response = await axios.get(
        "https://nohoo.site/api/oauth/kakao/login?code=" + authorize_code,
        null
      );

      console.log(response);
      AccessToken = response.data.data.accessToken;
      RefreshToken = response.data.data.refreshToken;
      console.log("여기는 토큰");
      console.log(AccessToken);
      console.log(RefreshToken);
      // requestUserInfo(AccessToken);
      setAccessToken(AccessToken);
      setRefreshToken(RefreshToken);

      // 토큰 발급에 성공한 경우 "Main"으로 이동
      navigation.navigate("Main", { screen: "StartingGame" });
      setLoading(false);
    } catch (error) {
      console.log("토큰 발급 오류:", error);
      // 오류 처리 또는 사용자에게 오류 메시지 표시
      // 토큰 발급에 실패한 경우 "Signin"으로 이동해야 하는데 일단은 Main으로 이동
      // navigation.navigate("Main", { screen: "StartingGame" });
      navigation.navigate("Signin", { screen: "Signin" });
    }
  };


  var source = {
    uri: `https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}`,
  };
  const [loading, setLoading] = useState(false);

  return (
    <View style={Styles.container}>
      {loading ? (
        <ActivityIndicator size="large" />
      ) : (
        <WebView
          style={{ flex: 1 }}
          originWhitelist={["*"]}
          scalesPageToFit={false}
          source={source}
          injectedJavaScript={INJECTED_JAVASCRIPT}
          javaScriptEnabled
          onMessage={(event) => {
            KakaoLoginWebView(event.nativeEvent["url"]);
          }}
        />
      )}
    </View>
  );
};

export default KaKaoLogin;

const Styles = StyleSheet.create({
  container: {
    flex: 1,
    marginTop: 24,
    backgroundColor: "#fff",
  },
});
