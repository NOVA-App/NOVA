import { createStackNavigator } from "@react-navigation/stack";

import MyRealEstateDetail from "./MyRealEstateDetail"
import {RealEstateMainPage} from "./RealEstateMainPage"
import ForSaleDetail from "./ForSaleComponent";

const RealEstatePageStack = createStackNavigator();

const RealEstatePage = () => {
  return (
    <RealEstatePageStack.Navigator headerMode="none" initialRouteName="RealEstateMainPage">
      <RealEstatePageStack.Screen name="RealEstateMainPage" component={RealEstateMainPage} />
      <RealEstatePageStack.Screen name="MyRealEstateDetail" component={MyRealEstateDetail} />
      <RealEstatePageStack.Screen name="ForSaleDetail" component={ForSaleDetail} />
    </RealEstatePageStack.Navigator>
  );
};

export default RealEstatePage;