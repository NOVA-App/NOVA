import styled from "styled-components/native";

export const Container = styled.View`
  flex-direction: row;
  align-items: center;
  height: ${(props) => props.height * 0.05};
`;

export const BarContainer = styled.View`
  justify-content: center;
  height: ${(props) => props.height * 0.05};
  border-radius: 20px;
  width: ${(props) => props.width * 0.7};
  background-color: ${(props) => props.bgColor || "#f3f2f2"};
`;

export const ProgressBar = styled.View`
  background-color: ${(props) => props.bgColor || "#f6dcbd"};
  width: ${(props) => 100 - (60 - props.age) * 2}%;
  align-items: flex-end;
  justify-content: center;
  height: 100%;
  border-radius: 10px;
  /* padding: 20px; */
`;
