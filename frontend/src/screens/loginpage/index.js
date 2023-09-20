import React from 'react';
import { View, Text, Image, TouchableOpacity, StyleSheet } from 'react-native';
import { useNavigation } from '@react-navigation/native'; // useNavigation 추가
import StackNavigator from '../../navigation/Stack'; // StackNavigator import 추가

const LoginPage = () => {
  const navigation = useNavigation(); // 네비게이션 객체 생성

  const handleLogin = () => {
    // 'SelectOptionPage'로 네비게이션 이동
    navigation.navigate('StartGame'); // 'StartGame'으로 변경
  };

  return (
    <View style={styles.container}>
      <Image
        source={require('../../assets/nova_logo.png')}
        style={styles.logo}
      />

      <TouchableOpacity
        style={styles.loginButton}
        onPress={handleLogin}
      >
        <Text style={styles.buttonText}>카카오톡으로 시작하기</Text>
      </TouchableOpacity>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  logo: {
    width: 200,
    height: 200,
    resizeMode: 'contain', // 로고 이미지 크기 조정
  },
  loginButton: {
    backgroundColor: 'gold',
    padding: 15,
    borderRadius: 10,
    marginTop: 20,
  },
  buttonText: {
    color: 'white',
    fontSize: 18,
    fontWeight: 'bold',
  },
});

export default LoginPage;
