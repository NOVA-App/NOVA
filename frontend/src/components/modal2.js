import { Modal, View, Text, Pressable, StyleSheet } from 'react-native';
import { Colors } from 'react-native/Libraries/NewAppScreen';
import styled from 'styled-components/native';
// import MaterialIcons from '@expo/vector-icons/MaterialIcons';

export default function Modal2({isVisible, children, onClose}) {
  return (
    // <Modal animationType="fade" transparent={true} visible={isVisible}>
    <Modal animationType="slide" transparent={true} visible={isVisible}>
    <StyledModalContent>
      <StyledTitleContainer>
        {/* <styledTitleText onPress={onClose}> 모달모달모달모달모달모달 ^0^</styledTitleText> */}
        <Text style={styles.Text} onPress={onClose}> X</Text>
      </StyledTitleContainer>
      {children}
    </StyledModalContent>
  </Modal>
  )
}

const StyledModalContent = styled.View`
    height: 70%;
    width: 70%;
    background-color: #EAFAF5;
    /* border-top-right-radius: 18;
    border-top-left-radius: 18; */
    border-radius: 20px;
    position: absolute;
    bottom: 15%;
    left: 15%;
`;
const StyledTitleContainer = styled.View`
    height: 15%;
    /* background-color: #464C55; */
    /* border-top-right-radius: 10;
    border-top-left-radius: 10; */
    /* padding-horizontal: 20; */
    /* flex-direction: row; */
    flex-direction: row-reverse;
    align-items: center;
    justify-content: space-between;
`;
const styles = StyleSheet.create({
  Text:{
    fontSize: 40,
    Colors: 'black',

  }
})
//   const styledTitleText = styled.Text`
//   color: '#fff';
//   font-size: 30;
// `