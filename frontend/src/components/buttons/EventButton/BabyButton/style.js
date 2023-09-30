import styled from "styled-components/native";

export const ButtonContainer = styled.TouchableOpacity`
  border-width: 0.5px;
  border-color: ${(props) => (props.bgColor ? props.bgColor : "#E8E8E8")};
  align-items: center;
  justify-content: end;
  width: 50px; /* 원하는 가로 크기 (픽셀 단위로) */
  height: 50px; /* 원하는 세로 크기 (픽셀 단위로) */
  border-radius: 5px;
  background-color: ${(props) => (props.bgColor ? props.bgColor : "#E8E8E8")};
  elevation: 2;
`;
