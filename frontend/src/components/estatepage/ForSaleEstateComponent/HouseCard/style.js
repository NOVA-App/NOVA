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

export const ImgBox = styled.Image`
  height: 100%;
  border-top-left-radius: 10px;
  border-bottom-left-radius: 10px;
`;

export const ContentContainer = styled.View`
  flex-direction: column;
  width: 60%;
`;
