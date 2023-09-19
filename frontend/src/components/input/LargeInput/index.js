import React from "react";
import PropTypes from 'prop-types';
import * as S from './style';

const InputLarge = (props) => {
  const handleTextChange = (text) => {
    // 숫자로 변환 시도
    const numericValue = parseInt(text, 10);

    // 정수로 변환된 값이 유효한 경우에만 콜백 호출
    if (!isNaN(numericValue)) {
      props.onChangeText(numericValue.toString());
    }
  };

  return (
    <S.StyledInputLarge
      placeholder={props.placeholder}
      maxLength={50}
      autoCapitalize='none'
      autoCorrect={false}
      returnKeyType='done'
      keyboardAppearance='dark'
      value={props.value}
      onChangeText={handleTextChange} // 수정된 핸들러로 변경
      onSubmitEditing={props.onSubmitEditing}
      keyboardType="numeric" // 숫자 키패드 사용
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
