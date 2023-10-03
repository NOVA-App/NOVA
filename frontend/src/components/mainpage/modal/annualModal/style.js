import styled from "styled-components/native";

const ModalContent = styled.View`
  height: 80%;
  width: 95%;
  background-color: #eafaf5;
  border-radius: 20px;
  position: absolute;
  bottom: 10%;
  left: 2.5%;
`;

const TitleContainer = styled.View`
  height: 10%;
  width: 100%;
  flex-direction: row-reverse;
  align-items: center;
  justify-content: space-between;
`;

const Text = styled.Text`
  font-size: 20px;
  font-weight: 800;
`;

export { ModalContent, TitleContainer, Text };
