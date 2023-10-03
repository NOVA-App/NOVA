import styled from 'styled-components/native';
import { useWindowDimensions } from 'react-native';

export const StyledInputSmall = styled.TextInput.attrs(({ theme }) => ({
  placeholderTextColor: theme.main,
}))`
  min-width: 30%;
  height: ${props => props.windowHeight * 0.06};
  margin: 3px 0;
  padding: 20px 15px;
  border-radius: 8px;
  font-size: 20px;
  background-color: white;
  color: black;
  border-width: 1px;
  border-color: gray;
`;
