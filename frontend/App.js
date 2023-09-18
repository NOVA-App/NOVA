import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View } from 'react-native';
import Modal2 from './src/components/modal2';
import StyledModalOpenButton from './src/components/btn';
import { useState } from 'react';

export default function App() {
  const [isModalVisible, setIsModalVisible] = useState(false);
  const onModalClose = () => {
    setIsModalVisible(false);
  };
  return (
    <View style={styles.container}>
      <Modal2 isVisible={isModalVisible} onClose={onModalClose}>
      </Modal2>
      
      <StyledModalOpenButton
        onPress={() => {
          setIsModalVisible(true);
        }}
      />
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
