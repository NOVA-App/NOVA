import { Modal, Dimensions, Text, StyleSheet } from 'react-native';
import styled from 'styled-components/native';
import * as S from './style'
import Button from '../../buttons/MediumButton/index'


const OneButtonLargeModal = props => {
  const styles = StyleSheet.create({
    Text:{
      fontSize: 40,
      Colors: 'black',
    }
  })
  return (
    // <Modal animationType="fade"  // 스타일 둘 중 하나 택 1
    <Modal animationType="slide" 
      transparent={true} 
      visible={props.isVisible}
      >
    <S.ModalContent>
      <S.TitleContainer>
        <Text 
          style={styles.Text} 
          onPress={props.onClose}
          > X</Text>
      </S.TitleContainer>
      <Text style={{ fontSize: 14 }}>{props.title}</Text>
      <S.ButtonContainer>
        <Button 
          title="확인"
          />
      </S.ButtonContainer>
    </S.ModalContent>
  </Modal>
  )
}



//   const styledTitleText = styled.Text`
//   color: '#fff';
//   font-size: 30;
// `

export default OneButtonLargeModal;