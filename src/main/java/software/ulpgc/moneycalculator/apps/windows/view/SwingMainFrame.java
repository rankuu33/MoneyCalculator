package software.ulpgc.moneycalculator.apps.windows.view;

import software.ulpgc.moneycalculator.apps.windows.control.Command;
import software.ulpgc.moneycalculator.apps.windows.model.Currency;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SwingMainFrame extends JFrame {

    private final CurrencyDialog currencyDialog;
    private final MoneyDialog moneyDialog;
    private final MoneyDisplay moneyDisplay;
    private final Map<String, Command> commands = new HashMap<>();


    public SwingMainFrame() throws HeadlessException {

        setTitle("Money Calculator");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.add(new JLabel("Initial currency:"));

        SwingMoneyDialog swingMoneyDialog = new SwingMoneyDialog();
        moneyDialog = swingMoneyDialog;
        panel.add(swingMoneyDialog);
        JPanel targetPanel = new JPanel();
        targetPanel.setLayout(new BoxLayout(targetPanel, BoxLayout.X_AXIS));
        targetPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        targetPanel.add(new JLabel("Target currency:"));

        SwingCurrencyDialog targetDialog = new SwingCurrencyDialog();
        currencyDialog = targetDialog;
        panel.add(targetDialog);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        buttonPanel.add(createChangeButton());
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        resultPanel.setBorder(new EmptyBorder(5, 5, 20, 5));
        SwingMoneyDisplay moneyDisplay2 = new SwingMoneyDisplay();
        moneyDisplay = moneyDisplay2;
        resultPanel.add(moneyDisplay2);

        add(panel);
        add(targetPanel);
        add(buttonPanel);
        add(resultPanel);
    }

    private Component createChangeButton() {
        JButton changeButton = new JButton("Change");
        changeButton.addActionListener(e -> {
            commands.get("exchange money").execute();
        });
        return changeButton;
    }


    private SwingMoneyDisplay createMoneyDisplay() {
        return new SwingMoneyDisplay();
    }

    private Component createCalculateButton() {
        JButton button = new JButton("Calculate");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commands.get("exchange").execute();
            }
        });
        return button;
    }

    public MoneyDialog moneyDialog() {
        return moneyDialog;
    }

    public CurrencyDialog currencyDialog() {
        return currencyDialog;
    }

    public MoneyDisplay moneyDisplay() {
        return moneyDisplay;
    }


    public void add(String operation, Command command) {
        commands.put(operation, command);
    }
}
