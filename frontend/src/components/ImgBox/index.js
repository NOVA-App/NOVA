import React from 'react';
import { Dimensions } from 'react-native';
import * as S from './style';
import BasicIcon from '../../assets/ProfileIcon.png';

const { width, height } = Dimensions.get('window');

const ImgBox = props => {
    return (
        <S.ImgContainer
          source= {props.ProfileUrl ? {uri : props.ProfileUrl} : BasicIcon}
          width= {width} 
        />
    )
}

export default ImgBox;