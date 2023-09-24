import React from "react";
import { Dimensions, View, Text, ScrollView } from "react-native";
import Budget from '../../../../components/budget/index'
import InstallmentSavingsCard from "./InstallmentSavings";
// import * as S from "./style";

const { height } = Dimensions.get("window");

const FinancialProduct = () => {

  return (
    <View style={styles.container}>
      <Budget />
      <View style={styles.upper}>
        <View>  
        <Text style={{fontSize: 20, marginBottom: 10}}>금융 | 계좌확인</Text>
        </View>
        <View style = {styles.lineStyle} />
      </View>
      <View>
        {/* <InstallmentSavingsCard /> */}
      </View>


    </View>
  );
};

export default FinancialProduct;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    // padding: 10,
    flexDirection: "column",
    backgroundColor: '#CDE8E5',
  },
  upper: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  content1:{
    flexDirection: "column",
    flex: 9,
    top: 20,
    alignItems: 'center',
  },
  lineStyle:{
    borderWidth: 0.5,
    width: 600,
    borderColor:'white',
    margin:0,
}
});