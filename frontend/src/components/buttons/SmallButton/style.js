import styled from 'styled-components/native';

export const ButtonContainer = styled.TouchableOpacity`
  border-width: 0.5px;
  border-color: ${props => props.bgColor ? props.bgColor : "#07CB89"};
  align-items:center;
  justify-content:center;
  height: ${props => props.height*(0.04)};
  border-radius: 5px;
  background-color: ${props => props.bgColor ? props.bgColor : "#07CB89"};
  width: 25%;
`;