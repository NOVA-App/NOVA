import { StyleSheet, Text, View, StatusBar, Dimensions, TouchableOpacity, Image } from 'react-native';
import React, { useState } from 'react';
import { useNavigation } from '@react-navigation/native'; // useNavigation 추가
import Budget from '../../components/budget/index'
import Button from '../../components/buttons/XXLargeButton'

export default function Banking() {

  const navigation = useNavigation();

  return (
    <View style={styles.container}>
      <Budget />
      <View style={styles.upper}>
        <Text style={{fontSize: 20}}>금융</Text>
      </View>
      <View style={styles.content1}>
        <Button title='계좌 확인'></Button>
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
  },
  content1:{
    flexDirection: "column",
    flex: 9,
    top: 20,
    alignItems: 'center',
  },
});