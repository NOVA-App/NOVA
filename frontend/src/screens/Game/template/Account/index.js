import { StyleSheet, Text, View, StatusBar, Dimensions, TouchableOpacity, Image } from 'react-native';
import React, { useState } from 'react';
import { useNavigation } from '@react-navigation/native'; // useNavigation 추가
import Budget from '../../../../components/budget/index'
import * as S from "./style";

export function Account() {

  const navigation = useNavigation();



  return (
    <View style={styles.container}>
      <Budget />
      <View style={styles.upper}>
        <View>  
        <Text style={{fontSize: 20, marginBottom: 10}}>금융 | 계좌확인</Text>
        </View>
        <View style = {styles.lineStyle} />
      </View>
      <View>
        
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