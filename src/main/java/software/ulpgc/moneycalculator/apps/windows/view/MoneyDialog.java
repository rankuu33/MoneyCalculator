package software.ulpgc.moneycalculator.apps.windows.view;

import software.ulpgc.moneycalculator.apps.windows.model.Currency;
import software.ulpgc.moneycalculator.apps.windows.model.Money;

import java.util.List;

public interface MoneyDialog {
    Money get();
    MoneyDialog define(List<Currency> currencies);

}
