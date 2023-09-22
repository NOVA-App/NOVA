import React from 'react';
import { Modal, Dimensions, Text, StyleSheet, View } from 'react-native';
import MyRealEstate from '../../components/MyRealEstateComponent';
import BBB from './b';
import CCC from './c';

const TabSelect = ({selectedTab}) => {
  return (
    <View>
      {selectedTab == 0 && <MyRealEstate/>}
      {selectedTab == 1 && <BBB/>}
      {selectedTab == 2 && <CCC/>}
    </View>
  );
};
export default TabSelect