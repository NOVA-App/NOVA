import { StyleSheet, Text, View, StatusBar, Dimensions, TouchableOpacity, Image } from 'react-native';
import React, { useState } from 'react';
import { useNavigation } from '@react-navigation/native'; // useNavigation 추가
import Budget from '../../../../components/budget/index'
import Button from '../../../../components/buttons/XXLargeButton'

export function Banking() {

  const navigation = useNavigation();

  const handleAccountButton = () => {
    // 'SelectOptionPage'로 네비게이션 이동
    navigation.navigate("Account"); // 'StartGame'으로 변경
  };
  
  return (
    <View style={styles.container}>
      <Budget />
      <View style={styles.upper}>
        <View>  
        <Text style={{fontSize: 20, marginBottom: 10}}>금융</Text>
        </View>
        <View style = {styles.lineStyle} />
      </View>
      <View style={styles.content1}>
        <Button title='계좌 확인' onPress={handleAccountButton} ></Button>
        <Button title='추가 납입 / 상품 가입'></Button>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    // padding: 10,
    flexDirection: "column",
    backgroundColor: '#CDE8E5',
  },
  upper: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  content1:{
    flexDirection: "column",
    flex: 9,
    top: 20,
    alignItems: 'center',
  },
  lineStyle:{
    borderWidth: 0.5,
    width: 600,
    borderColor:'white',
    margin:0,
}
});