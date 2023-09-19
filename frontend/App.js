import { StyleSheet, Text, View } from 'react-native';
import OneButtonSmallModal from './src/components/modals/OneButtonSmallModal';
import StyledModalOpenButton from './src/components/btn';
import { useState } from 'react';

export default function App() {
  // const [isModalVisible, setIsModalVisible] = useState(false);
  // const onModalClose = () => {
  //   setIsModalVisible(false);
  return (
    <View style={styles.container}>
      <Text>Open up App.js to start working on your app!</Text>
      <StatusBar style="auto" />
    </View>
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
