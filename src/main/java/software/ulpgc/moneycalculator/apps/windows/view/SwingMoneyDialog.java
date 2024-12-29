package software.ulpgc.moneycalculator.apps.windows.view;

import software.ulpgc.moneycalculator.apps.windows.model.Currency;
import software.ulpgc.moneycalculator.apps.windows.model.Money;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SwingMoneyDialog extends JPanel implements MoneyDialog {
    private final TextField amount;
    private SwingCurrencyDialog currencyDialog;

    public SwingMoneyDialog() {
        this.setLayout(new FlowLayout());
        this.add(new JLabel("Amount:"));
        this.add(amount = createAmountInput());
    }

    private TextField createAmountInput() {
        TextField textField = new TextField();
        textField.setColumns(8);
        return textField;
    }

    private Component createCurrencyDialog(List<Currency> currencies) {
        SwingCurrencyDialog currencyDialog = new SwingCurrencyDialog();
        currencyDialog.define(currencies);
        this.currencyDialog = currencyDialog;
        return currencyDialog;
    }

    @Override
    public Money get() {
        try {
            double moneyAmount = Double.parseDouble(amount.getText());
            return new Money(moneyAmount, currencyDialog.get());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please, introduce a valid number", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    @Override
    public MoneyDialog define(List<Currency> currencies) {
        add(createCurrencyDialog( currencies ));
        return this;
    }

}
