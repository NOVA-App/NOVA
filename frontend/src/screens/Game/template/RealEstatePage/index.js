import { createStackNavigator } from "@react-navigation/stack";

import MyRealEstateDetail from "./MyRealEstateDetail"
import {RealEstateMainPage} from "./RealEstateMainPage"

const RealEstatePageStack = createStackNavigator();

const RealEstatePage = () => {
  return (
    <RealEstatePageStack.Navigator headerMode="none" initialRouteName="RealEstateMainPage">
      <RealEstatePageStack.Screen name="RealEstateMainPage" component={RealEstateMainPage} />
      <RealEstatePageStack.Screen name="MyRealEstateDetail" component={MyRealEstateDetail} />
    </RealEstatePageStack.Navigator>
  );
};

export default RealEstatePage;