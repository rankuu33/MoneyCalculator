package software.ulpgc.moneycalculator.apps.windows.view;

import software.ulpgc.moneycalculator.apps.windows.model.Money;

import javax.swing.*;
import java.text.DecimalFormat;

public class SwingMoneyDisplay extends JPanel implements MoneyDisplay {
    private final JLabel resultLabel;
    private final JLabel valueLabel;

    public SwingMoneyDisplay() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.resultLabel = new JLabel("Conversion: ");
        this.valueLabel = new JLabel();
        add(resultLabel);
        add(valueLabel);
    }

    @Override
    public void show(Money money) {
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        String formattedValue = decimalFormat.format(money.amount());
        valueLabel.setText(formattedValue + money.currency().symbol());
    }
}
