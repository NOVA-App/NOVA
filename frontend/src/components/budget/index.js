import React, { useState } from 'react';
import { StyleSheet, Text, View, StatusBar, Dimensions, TouchableOpacity, Image } from 'react-native';
import Logo from '../../assets/nova_logo.png'


const { width, height } = Dimensions.get('window');

const Budget = props => {
    return (
      <View style={styles.upper}>
        <View style={{flex: 1}}>
          <Image style={{resizeMode: "contain", width: '100%', alignItems:'flex-start'}} source={Logo} />
        </View>
        <View style={{alignItems:'center', flex: 2}}>
          <Text style={{fontSize: 24, fontWeight: 'bold', }}>여유자금</Text>
          <Text style={{fontSize: 20, color:'#F5B700' }}>2,000,000원</Text>
        </View>
        <View style={{flex:1, alignItems:'flex-end'}}> 
          <Text style={{fontSize: 15, marginTop: 20}} >메뉴</Text>
        </View>
      </View>
    )
}

const styles = StyleSheet.create({
  upper:{
    flex: 1,
    flexDirection: "row",
    // justifyContent: 'center',
    // backgroundColor: 'red'
  },
});
export default Budget;