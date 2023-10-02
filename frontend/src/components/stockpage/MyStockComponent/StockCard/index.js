import React from 'react';
import { View, Text, TouchableOpacity } from 'react-native';
import * as S from './style';
import SmallButton from '../../../buttons/SmallButton';
import { useNavigation } from '@react-navigation/native';

const StockCard = (props) => {
  const navigation = useNavigation();

  const handleDetailPress = () => {
    navigation.navigate("StockDetailPage", { screen: "StockDetailPage" });
  };

  return (
    <S.Container height={props.height}>
      <S.StockContainer>
        <Text style={{ fontSize: 25 }}>00전자</Text>
        <Text style={{ fontSize: 25, color: '#D90452' }}>550,000</Text>
        <Text style={{ fontSize: 25, color: '#D90452' }}>+100%</Text>
      </S.StockContainer>
      <S.ContentContainer>
        <S.TextContainer>
          <S.MiddleText>{`투자액`}</S.MiddleText>
          <S.MiddleText>{`150,000,000`}</S.MiddleText>
        </S.TextContainer>
        <S.TextContainer>
          <S.MiddleText>{`현재가`}</S.MiddleText>
          <S.MiddleText>{`300,000,000`}</S.MiddleText>
        </S.TextContainer>
        <S.TextContainer>
          <S.MiddleText>{`보유 수량`}</S.MiddleText>
          <S.MiddleText>{`500`}</S.MiddleText>
        </S.TextContainer>
        <View
          style={{
            marginTop: '2%',
            flexDirection: 'row',
            justifyContent: 'flex-end',
          }}
        >
          <TouchableOpacity >
            <SmallButton title="상세보기" bgColor="#0046FF" onPress={handleDetailPress} />
          </TouchableOpacity>
        </View>
      </S.ContentContainer>
    </S.Container>
  );
};

export default StockCard;
