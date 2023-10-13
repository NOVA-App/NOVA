import styled from 'styled-components/native';

export const PageContainer = styled.View`
  flex: 1;
  align-items: center;
  justify-content: flex-start;
  margin-top: 20px;
`;

export const ToggleButton = styled.TouchableOpacity`
  border-radius: 10px;
  flex-direction: row;
  align-items: center;
  padding: 10px;
  background-color: white;
  margin-vertical: 10px;
`;

export const TextContainer = styled.View`
  flex: 1;
`;

export const DescriptionText = styled.Text`
  font-size: 23px;
  font-weight: bold;
  color: black;
  text-align: left;
`;
