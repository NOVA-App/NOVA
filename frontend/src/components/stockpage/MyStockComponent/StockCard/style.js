import styled from "styled-components/native";

export const Container = styled.View`
  background-color: white;
  width: 95%;
  height: ${(props) => props.height * 0.15};
  border-radius: 10px;
  margin-bottom: 1%;
  border-color: gray;
  border-width: 0.5px;
  flex-direction: row;
  margin-left: auto;
  margin-right: auto;
`;

export const MiddleText = styled.Text`
  font-size: 16px;
`;

export const ContentContainer = styled.View`
  flex-direction: column;
  min-width: 65%;
`;

export const StockContainer = styled.View`
  width: 30%;
  align-items: center;
  justify-content: space-between;
`;

export const TextContainer = styled.View`
  flex-direction: row;
  justify-content: space-evenly;
`;
