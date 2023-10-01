import React from 'react';
import { TouchableOpacity, Text, StyleSheet } from 'react-native';

const ToggleButtonSaving = ({ label, isSelected, onPress }) => {
  return (
    <TouchableOpacity
      style={[styles.button, isSelected ? styles.selected : {}]}
      onPress={onPress}
    >
      <Text style={[styles.label, isSelected ? styles.selectedText : {}]}>
        {label}
      </Text>
    </TouchableOpacity>
  );
};

const styles = StyleSheet.create({
  button: {
    borderWidth: 1,
    borderColor: 'black',
    padding: 10,
    margin: 5,
  },
  selected: {
    backgroundColor: 'green', //선택된 버튼 색
  },
  label: {
    fontSize: 25,
    fontWeight: 'bold',
  },
  selectedText: {
    color: 'white', // 선택된 버튼 글자색
  },
});

export default ToggleButtonSaving;
