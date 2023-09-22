import { StyleSheet, Text, View, StatusBar, Dimensions, TouchableOpacity, Image } from 'react-native';
import React, { useState } from 'react';
import { useNavigation } from '@react-navigation/native'; // useNavigation 추가
import Budget from '../../../../../../../../components/budget/index'
import Button from '../../../../../../../../components/buttons/SmallButton';
import Marriage from '../../../../../../../../assets/Marriage.png'


export default function MarriagePage() {
  const navigation = useNavigation(); // 네비게이션 객체 생성
  
  // 나중에 recoil에 저장하기
  const [gender, setGender] = useState('남');

  const handleNext = () => {
    //
    navigation.navigate('LoginPage'); // 'LoginPage'로 변경
  };

  // const { width, height } = Dimensions.get('window');
  const windowWidth = Dimensions.get('window').width
  const windowHeight = Dimensions.get('window').Height
  

  return (
    <View style={styles.container}>
      <Budget /> 
      <View style={styles.content1}>
        <Text style={{fontSize: 23, fontWeight: 'bold'}}>결혼하기</Text>
        <Text style={{fontSize: 15}}>소요금액: 2000만원</Text>
      </View> 
      <View style={styles.content2}>
        <Text style={{fontSize: 22, marginBottom: 30}}>결혼을 진행하시겠습니까?</Text>
        <View style={{flexDirection: "row"}}> 
          <View style={{marginRight: 15}}> 
          <Button  title='예' bgColor='#0046FF'></Button>
          </View>
          <View style={{marginLeft: 15}}> 
          <Button title='아니오' bgColor='#D90452'></Button>
          </View>
        </View>
      </View>
      <View style={styles.content3}>
        <Image 
          source={Marriage} 
          style={styles.img}
          />
      </View>
          
    </View>
  );
  };

  const styles = StyleSheet.create({
    container: {
      flex: 1,
      justifyContent: 'center',
      alignItems: 'center',
      // backgroundColor: 'blue',
      padding: 10,
      flexDirection: "column",
    },
    content1:{
      flexDirection: "column",
      flex: 2,
      top: 20,
      alignItems: 'center',
    },
    content2:{
      flexDirection: "column",
      flex: 2,
      // top: 20,
      alignItems: 'center',
    },
    content3:{
      flexDirection: "column",
      flex: 3,
      // top: 20,
      alignItems: 'center',
    },
    img: {
      width: 200,
      height: 200,
    }
  });