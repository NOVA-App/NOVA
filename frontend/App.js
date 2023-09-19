import { StyleSheet, Text, View } from 'react-native';
import OneButtonSmallModal from './src/components/modals/OneButtonSmallModal';
import StyledModalOpenButton from './src/components/btn';
import { useState } from 'react';

export default function App() {
  const [isModalVisible, setIsModalVisible] = useState(false);
  const onModalClose = () => {
    setIsModalVisible(false);
  };
  return (
    <View style={styles.container}>
      <OneButtonSmallModal 
        isVisible={isModalVisible}
        onClose={onModalClose}
        >
      </OneButtonSmallModal>
      
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
