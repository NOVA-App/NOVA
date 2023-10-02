import React from "react";
import PropTypes from 'prop-types';
import * as S from './style'; 

const InputSmallText = (props) => {
  return (
    <S.StyledInputSmall
      placeholder={props.placeholder}
      maxLength={50}
      autoCapitalize='none'
      autoCorrect={false}
      returnKeyType='done'
      keyboardAppearance='dark'
      value={props.value}
      onChangeText={() => {
      }}
      onSubmitEditing={props.onSubmitEditing}
    />
  );
};

InputSmall.propTypes = {
  placeholder: PropTypes.string,
  value: PropTypes.string.isRequired,
  onChangeText: PropTypes.func.isRequired,
  onSubmitEditing: PropTypes.func.isRequired,
};

export default InputSmallText;