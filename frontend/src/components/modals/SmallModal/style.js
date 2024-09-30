import styled from 'styled-components/native';


const ModalContent = styled.View`
    height: 40%;
    width: 80%;
    background-color: #EAFAF5;
    border-radius: 20px;
    position: absolute;
    bottom: 30%;
    left: 10%;
`;
const TitleContainer = styled.View`
    height: 10%;
    flex-direction: row-reverse;
    align-items: center;
    justify-content: space-between;
`;

export { ModalContent, TitleContainer}