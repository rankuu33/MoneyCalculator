package software.ulpgc.moneycalculator.apps.windows.view;

import software.ulpgc.moneycalculator.apps.windows.model.Currency;

import javax.swing.*;
import java.util.List;

public class SwingCurrencyDialog extends JPanel implements CurrencyDialog {
    private List<Currency> currencies;
    private JComboBox<Currency> selector;



    private JComboBox<Currency> createSelector(List<Currency> currencies) {
        JComboBox<Currency> comboBox = new JComboBox<>();
        for (Currency currency : currencies)
            comboBox.addItem(currency);
        return comboBox;
    }

    @Override
    public Currency get() {
        return currencies.get(selector.getSelectedIndex());
    }

    @Override
    public CurrencyDialog define(List<Currency> currencies) {
        this.currencies = currencies;
        add(selector = createSelector(currencies));
        return this;
    }
}
