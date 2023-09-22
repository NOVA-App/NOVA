import React from 'react';
import { Modal, Dimensions, Text, StyleSheet, View } from 'react-native';
import AAA from './a';
import BBB from './b';
import CCC from './c';

const TabSelect = ({selectedTab}) => {
  return (
    <View>
      {selectedTab == 0 && <AAA/>}
      {selectedTab == 1 && <BBB/>}
      {selectedTab == 2 && <CCC/>}
    </View>
  );
};
export default TabSelect