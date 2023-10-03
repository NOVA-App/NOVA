import React from "react";
import PropTypes from 'prop-types';
import * as S from './style'; 

const TextInputSmall = (props) => {
  return (
    <S.StyledInputSmall
      placeholder={props.placeholder}
      maxLength={50}
      autoCapitalize='none'
      autoCorrect={false}
      returnKeyType='done'
      keyboardAppearance='dark'
      value={props.value}
      onChangeText={props.onChangeText}
      onSubmitEditing={props.onSubmitEditing}
    />
  );
};

TextInputSmall.propTypes = {
  placeholder: PropTypes.string,
  value: PropTypes.string.isRequired,
  onChangeText: PropTypes.func.isRequired,
  onSubmitEditing: PropTypes.func.isRequired,
};

export default TextInputSmall;
