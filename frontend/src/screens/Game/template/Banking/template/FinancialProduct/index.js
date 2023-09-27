import React from "react";
import { Dimensions, View, Text, ScrollView, StyleSheet } from "react-native";
import InstallmentSavingsCard from "../../../../../../components/InstallmentSavings/index";
import IRPCard from "../../../../../../components/IRPCards/index";
// import * as S from "./style";

const { height, width } = Dimensions.get("window");

const FinancialProduct = () => {

  return (
    <View style={styles.container}>
      <View style={styles.upper}>
        <View>  
        <Text style={{fontSize: 20, marginBottom: 10}}>금융 | 추가납입 / 상품가입</Text>
        </View>
        <View style = {styles.lineStyle} />
      </View>
      <View style={styles.content1}>
        <InstallmentSavingsCard />
        {/* <IRPCard /> */}
      </View>
      <View style={styles.content1}>
        <IRPCard />
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
    // flexDirection: "column",
    flex: 4,
    alignItems: 'center',
    width: '90%',
    height: '90%',
  },
  lineStyle:{
    borderWidth: 0.5,
    width: 600,
    borderColor:'white',
    margin:0,
}

});