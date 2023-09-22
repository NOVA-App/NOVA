import React, { useState } from 'react';
import { StyleSheet, Text, View, StatusBar, Dimensions, TouchableOpacity, Image } from 'react-native';
import Logo from '../../assets/nova_logo.png'
import styled from 'styled-components/native';

// const { width, height } = Dimensions.get('window');
// const windowWidth = Dimensions.get('window').width
// const windowHeight = Dimensions.get('window').Height


const Budget = props => {
  const width = Dimensions.get('window').width
    return (
      <StyledUpper width={width} >
        <View style={{flex: 1, alignItems:'flex-start', width: 100}}>
          <Image style={{resizeMode: "contain", width: '100%', marginLeft: 5 }} source={Logo} />
        </View>
        <View style={{alignItems:'center', flex: 2, justifyContent: 'center'}}>
          <Text style={{fontSize: 24, fontWeight: 'bold', }}>여유자금</Text>
          <Text style={{fontSize: 20, color:'#F5B700' }}>2,000,000원</Text>
        </View>
        <View style={{flex:1, alignItems:'flex-end', justifyContent: 'center'}}> 
          <Text style={{fontSize: 17}} >메뉴</Text>
        </View>
      </StyledUpper>
    )
}

export default Budget;



// const styles = StyleSheet.create({
//   upper:{
//     flex: 1,
//     flexDirection: "row",
//     // justifyContent: 'center',
//     backgroundColor: 'white',
//     padding: 10,
//     // width: windowWidth,
//   },
// });

const StyledUpper = styled.View`
  /* width: ${({width}) => width - 40}px; */
  width: ${({width}) => width}px;
  /* width: 100%; */
  flex: 1;
  flex-direction: row;
  background-color: white;
  margin-top: 0;
  /* padding: 10; */
`;