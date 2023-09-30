import { Account, BankingMainPage, FinancialProduct } from "./template";
import { createStackNavigator } from "@react-navigation/stack";

const BankingPageStack = createStackNavigator();

export default function Banking() {
    return (
        
        <BankingPageStack.Navigator>
            <BankingPageStack.Screen name="BankingMainPage" component={BankingMainPage} initialParams={BankingMainPage} options={{headerShown: false}}/>
            <BankingPageStack.Screen name="Account" component={Account} options={{headerShown: false}}/>
            <BankingPageStack.Screen name="FinancialProduct" component={FinancialProduct} options={{headerShown: false}}/>
        </BankingPageStack.Navigator>
    )
}