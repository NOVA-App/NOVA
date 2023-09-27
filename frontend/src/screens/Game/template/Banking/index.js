import { Account, BankingMainPage, FinancialProduct } from "./template";
import { createStackNavigator } from "@react-navigation/stack";

const BankingPageStack = createStackNavigator();

export default function Banking() {
    return (
        
        <BankingPageStack.Navigator>
            <BankingPageStack.Screen name="BankingMainPage" component={BankingMainPage} initialParams={BankingMainPage}/>
            <BankingPageStack.Screen name="Account" component={Account}/>
            <BankingPageStack.Screen name="FinancialProduct" component={FinancialProduct}/>
        </BankingPageStack.Navigator>
    )
}