import React from "react";
import { Modal, Dimensions, Text, StyleSheet, View } from "react-native";
import MyRealEstate from "../../../../components/estatepage/MyRealEstateComponent";
import ForSaleEstate from "../../../../components/estatepage/ForSaleEstateComponent";
import LoanEstate from "../../../../components/estatepage/LoanEstateComponent";

const TabSelect = ({ selectedTab }) => {
  return (
    <View>
      {selectedTab == 0 && <MyRealEstate />}
      {selectedTab == 1 && <ForSaleEstate />}
      {selectedTab == 2 && <LoanEstate />}
    </View>
  );
};
export default TabSelect;
