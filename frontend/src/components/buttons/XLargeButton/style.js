import styled from "styled-components/native";

export const ButtonContainer = styled.TouchableOpacity`
  border-width: 0.5px;
  border-color: ${(props) => (props.bgColor ? props.bgColor : "gray")};
  align-items: center;
  justify-content: center;
  height: ${(props) => props.height * 0.11};
  border-radius: 5px;
  background-color: ${(props) => (props.bgColor ? props.bgColor : "#038C7F")};
  min-width: 98%;
  opacity: 0.4;
`;
