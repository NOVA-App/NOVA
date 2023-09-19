import styled from 'styled-components/native';


const ModalContent = styled.View`
    height: 70%;
    width: 80%;
    background-color: #EAFAF5;
    border-radius: 20px;
    position: absolute;
    bottom: 15%;
    left: 10%;
`;
const TitleContainer = styled.View`
    height: 15%;
    flex-direction: row-reverse;
    align-items: center;
    justify-content: space-between;
`;
const ButtonContainer = styled.View`
    top: 60%;
    left: 25%
`;

export { ModalContent, TitleContainer, ButtonContainer}