import styled from "styled-components/native";

export const Container = styled.View`
  flex-direction: row;
  align-items: center;
`;

export const ImgContainer = styled.Image`
  width: 27%;
  height: 30%;
  border: 0.5px;
  border-radius: 10px;
`;

export const UserContainer = styled.View`
  flex-direction: row;
  align-items: center;
  height: 25%;
`;

export const ChangeButton = styled.TouchableOpacity`
  background-color: #f8fa9d;
  width: 10%;
  height: 10%;
  font-size: 15px;
`;

export const Input = styled.TextInput.attrs(({ theme }) => ({
  placeholderTextColor: theme.main,
}))`
  width: 70%;
  height: 20%;
  border-radius: 12px;
  font-size: 20px;
  background-color: white;
  color: black;
  border-width: 1px;
  border-color: gray;
  text-align: center;
`;