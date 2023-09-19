// style.js
import styled from 'styled-components/native';
import { useWindowDimensions } from 'react-native';

export const StyledInputMedium = styled.TextInput.attrs(({ theme }) => ({
  placeholderTextColor: theme.main,
}))`
  width: 50%;
  height: ${props => props.windowHeight * 0.07}; 
  margin: 3px 0;
  padding: 20px 18px;
  border-radius: 10px;
  font-size: 24px; 
  background-color: white;
  color: black;
  border-width: 1px;
  border-color: gray;
`;
