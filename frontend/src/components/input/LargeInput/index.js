import React from "react";
import PropTypes from 'prop-types';
import * as S from './style';

const InputLarge = ({ placeholder, value, onChangeText, onSubmitEditing }) => {
  return (
    <S.StyledInputLarge
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

InputLarge.propTypes = {
  placeholder: PropTypes.string,
  value: PropTypes.string.isRequired,
  onChangeText: PropTypes.func.isRequired,
  onSubmitEditing: PropTypes.func.isRequired,
};

export default InputLarge;
