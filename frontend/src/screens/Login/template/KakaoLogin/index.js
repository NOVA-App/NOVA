import React from "react";
import { View, StyleSheet } from "react-native";
import { WebView } from "react-native-webview";

const REST_API_KEY = "ad1df559dc905b03ec9d59ad4afbe212";
const REDIRECT_URI = "http://nohoo.site/api/oauth/kakao/callback";

const KaKaoLogin = () => {
  return (
    <View style={Styles.container}>
      <WebView
        style={{ flex: 1 }}
        originWhitelist={["*"]}
        scalesPageToFit={false}
        source={{
          uri: `https://kauth.kakao.com/oauth/authorize?client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}&response_type=code`,
        }}
      />
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
