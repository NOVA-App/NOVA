import styled from 'styled-components/native';

const ImgContent = styled.Image`
  width: ${props => props.width * 0.10}px;
  height: ${props => props.height * 0.10}px;
  background-color: red;
  `;

const ImgContainer = styled.View`
  /* margin-top: 10px; */
  background-color: red;
  /* flex: 1; */
`;

const CompoContainer = styled.View`
  /* flex: 4; */
  background-color: yellow;
`;


export {ImgContent, ImgContainer, CompoContainer};