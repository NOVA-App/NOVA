import styled from "styled-components/native";

export const Container = styled.View`
  background-color: white;
  width: 95%;
  height: ${(props) => props.height * 0.9};
  border-radius: 10px;
  margin-bottom: 1%;
  border-color: gray;
  border-width: 0.5px;
  margin-left: 2.5%;
`;

export const MiddleText = styled.Text`
  font-size: 16px;
  margin-right: 20px;
`;

export const ImgBox = styled.Image`
  height: 100%;
  border-top-left-radius: 10px;
  border-bottom-left-radius: 10px;
`;



export const SmallContainer = styled.View`
  flex-direction: row;
  flex: 4;
  width: 100%;
  align-items: center;
  justify-content: center;
`;

export const TagContainer = styled.View`
  background-color: #038c7f;
  width: 30%;
  height: 100%;
  /* height: 50px; */
  justify-items: flex-start;
  align-items: center;
  border-radius: 10px;
  margin-top: 3%;
  flex: 0.5;
`;

export const InfoContainer = styled.View`
  /* height: 50px; */
  /* justify-items: flex-start; */
  align-items: flex-end;
  border-radius: 10px;
  margin-top: -5%;
  margin-right: 3%;
  flex: 0.5;
`;



