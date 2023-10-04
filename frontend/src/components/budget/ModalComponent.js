// ModalComponent.js

import React from 'react';
import { View, Text, Modal, TouchableOpacity } from 'react-native';

const ModalComponent = ({ isVisible, onClose }) => {
  return (
    <Modal
      visible={isVisible}
      animationType="slide"
      transparent={true}
    >
      <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
        <View style={{ backgroundColor: 'white', padding: 20, borderRadius: 10, width: 300 }}>
          {/* 모달 내용 */}
          <Text>내용을 입력하세요...
          입력하세요
          입력하세요
          입력하세요
          입력하세요
          입력하세요
          입력하세요
          입력하세요
          입력하세요
          입력하세요
          </Text>
          
          {/* X 아이콘을 터치할 때 모달을 닫음 */}
          <TouchableOpacity style={{ position: 'absolute', top: 10, right: 10 }} onPress={onClose}>
            <Text>X</Text>
          </TouchableOpacity>
        </View>
      </View>
    </Modal>
  );
};

export default ModalComponent;
