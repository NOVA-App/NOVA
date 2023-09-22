import styled from "styled-components/native";

export const Container = styled.View`
  flex-direction: row;
  justify-content: center;
  align-items: center;
  height: ${(props) => props.height * 0.1};
`;

export const BoxContainer = styled.View`
  background-color: #e7f7f6;
  justify-content: center;
  align-items: center;
  flex-direction: row;
  justify-content: center;
  width: 100%;
  height: ${(props) => props.height * 0.1};
  padding-left: 3%;
`;

export const BarContainer = styled.View`
  flex-direction: row;
  justify-content: flex-start;
  height: ${(props) => props.height * 0.07};
  border-radius: 20px;
  width: 70%;
  background-color: #f3f2f2;
`;

export const PosessionBox = styled.View`
  background-color: ${(props) => (props.bgColor ? props.bgColor : "#F96464")};
  justify-content: center;
  align-items: center;
  width: 33%;
  height: ${(props) => props.height * 0.07};
`;

export const ProgressBar = styled.View`
  background-color: #f6dcbd;
  width: ${(props) => 100 - (60 - props.age) * 2}%;
  align-items: flex-end;
  justify-content: center;
  height: 100%;
  border-radius: 10px;
`;
