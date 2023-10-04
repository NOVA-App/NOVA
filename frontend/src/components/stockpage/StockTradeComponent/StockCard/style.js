import styled from "styled-components/native";

export const Container = styled.View`
  background-color: white;
  width: 95%;
  max-height: 20%;
  border-radius: 10px;
  margin-bottom: 1%;
  border-color: gray;
  border-width: 0.5px;
  margin-left: auto;
  margin-right: auto;
  padding: 2%;
`;

export const MiddleText = styled.Text`
  font-size: 25px;
`;

export const ImgBox = styled.Image`
  height: 100%;
  border-top-left-radius: 10px;
  border-bottom-left-radius: 10px;
`;

export const ContentContainer = styled.View`
  flex-direction: row;
  min-width: 20%;
  justify-content: space-evenly;
`;
