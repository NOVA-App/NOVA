import React from "react";
import styled from "styled-components/native";
import Button from "../../../../../../components/buttons/LargeButton";

const Container = styled.View`
  align-items: center;
`;
const StyledText = styled.Text`
  font-size: 30px;
  margin: 10px;
`;

const ChooseOptions = () => {
  return (
    <Container>
      <StyledText>시작하기</StyledText>
      <Button title="List" />
    </Container>
  );
};

export default ChooseOptions;
