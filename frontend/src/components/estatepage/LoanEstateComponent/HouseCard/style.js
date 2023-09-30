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
  margin-left: 2.5%;
`;

export const MiddleText = styled.Text`
  font-size: 16px;
`;

export const PercentText = styled.Text`
  font-size: 16px;
  color: #d90452;
`;

export const ImgBox = styled.Image`
  width: 35%;
  height: 100%;
  border-top-left-radius: 10px;
  border-bottom-left-radius: 10px;
`;

export const ContentContainer = styled.View`
  flex-direction: column;
  width: 65%;
`;

export const TextContainer = styled.View`
  flex-direction: row;
  justify-content: space-evenly;
`;
