package software.ulpgc.moneycalculator.apps.windows.view;

import software.ulpgc.moneycalculator.apps.windows.model.Currency;

import java.util.List;

public interface CurrencyDialog {
    Currency get();
    CurrencyDialog define(List<Currency> currencies);
}
