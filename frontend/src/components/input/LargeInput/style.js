import styled from "styled-components/native";

export const StyledInputLarge = styled.TextInput.attrs(({ theme }) => ({
  placeholderTextColor: theme.main,
}))`
  width: 70%;
  height: ${({ windowHeight }) => windowHeight * 0.07}px;
  margin: 3px 0;
  padding: 20px 25px;
  border-radius: 12px;
  font-size: 30px;
  background-color: white;
  color: black;
  border-width: 1px;
  border-color: gray;
`;
