import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import StackNavigator from './src/navigation/Stack'; // import 수정

// import { useState } from 'react';

export default function App() {
  // const [isModalVisible, setIsModalVisible] = useState(false);
  // const onModalClose = () => {
  //   setIsModalVisible(false);
  return (
    <NavigationContainer>
        <StackNavigator />
    </NavigationContainer>
  );
}


const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
