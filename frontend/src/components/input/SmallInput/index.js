import React from "react";
import PropTypes from 'prop-types';
import * as S from './style'; 

const InputSmall = ({ placeholder, value, onChangeText, onSubmitEditing }) => {
  return (
    <S.StyledInputSmall
      placeholder={placeholder}
      maxLength={50}
      autoCapitalize='none'
      autoCorrect={false}
      returnKeyType='done'
      keyboardAppearance='dark'
      value={value}
      onChangeText={onChangeText}
      onSubmitEditing={onSubmitEditing}
    />
  );
};

InputSmall.propTypes = {
  placeholder: PropTypes.string,
  value: PropTypes.string.isRequired,
  onChangeText: PropTypes.func.isRequired,
  onSubmitEditing: PropTypes.func.isRequired,
};

export default InputSmall;
