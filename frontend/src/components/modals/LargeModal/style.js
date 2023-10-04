import styled from "styled-components/native";

const ModalContent = styled.View`
  height: 70%;
  width: 80%;
  background-color: #eafaf5;
  border-radius: 20px;
  position: absolute;
  bottom: 15%;
  left: 10%;
`;
const TitleContainer = styled.View`
  height: 15%;
  flex-direction: row-reverse;
  align-items: center;
  justify-content: space-between;
`;

export { ModalContent, TitleContainer };
