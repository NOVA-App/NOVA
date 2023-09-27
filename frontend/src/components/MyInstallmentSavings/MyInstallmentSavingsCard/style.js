import styled from "styled-components/native";

export const Container = styled.View`
  background-color: #F5B700;
  width: 95%;
  height: ${(props) => props.height * 0.2};
  border-radius: 10px;
  margin-bottom: 1%;
  border-color: gray;
  border-width: 0.5px;
  margin-left: 2.5%;
`;

export const MiddleText = styled.Text`
  font-size: 16px;
  margin-right: 20px;
  color: white;
`;

export const PropsMiddleText = styled.Text`
  font-size: 16px;
  margin-right: 20px;
`;


export const SmallContainer = styled.View`
  flex-direction: row;
  flex: 1;
  width: 100%;
  align-items: center;
  justify-content: center;
`;



