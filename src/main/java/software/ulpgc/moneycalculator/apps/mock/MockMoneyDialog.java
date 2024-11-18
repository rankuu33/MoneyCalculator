/*package software.ulpgc.moneycalculator.apps.mock;

import software.ulpgc.moneycalculator.apps.model.Currency;
import software.ulpgc.moneycalculator.apps.model.Money;
import software.ulpgc.moneycalculator.apps.windows.view.MoneyDialog;

import java.util.List;

public class MockMoneyDialog implements MoneyDialog {
    private final List<Currency> currencies;

    public MockMoneyDialog(List<Currency> currencies) {
        this.currencies = currencies;
    }

    @Override
    public Money get() {
        return new Money(100, currencies.getLast());
    }
}
*/