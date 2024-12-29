package software.ulpgc.moneycalculator.apps.windows.view;

import software.ulpgc.moneycalculator.apps.windows.control.Command;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SwingMainFrame extends JFrame {




    private CurrencyDialog currencyDialog;
    private MoneyDialog moneyDialog;
    private MoneyDisplay moneyDisplay;
    private final Map<String, Command> commands = new HashMap<>();

    public SwingMainFrame() throws HeadlessException {

        setTitle("Money Calculator");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);


        Color backgroundColor = new Color(230, 240, 255); // Color azul claro
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(backgroundColor);
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));


        mainPanel.add(createInputPanel(backgroundColor), BorderLayout.CENTER);
        mainPanel.add(createButtonPanel(backgroundColor), BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JPanel createInputPanel(Color backgroundColor) {
        JPanel panel = new JPanel();
        panel.setBackground(backgroundColor);
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);


        JLabel initialCurrencyLabel = new JLabel("Initial currency:");
        JLabel targetCurrencyLabel = new JLabel("Target currency:");
        JLabel resultLabel = new JLabel("Conversion Result:");

        SwingMoneyDialog swingMoneyDialog = new SwingMoneyDialog();
        moneyDialog = swingMoneyDialog;

        SwingCurrencyDialog initialCurrencyDialog = new SwingCurrencyDialog();
        SwingCurrencyDialog targetCurrencyDialog = new SwingCurrencyDialog();
        currencyDialog = targetCurrencyDialog;

        SwingMoneyDisplay display = new SwingMoneyDisplay();
        moneyDisplay = display;

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(initialCurrencyLabel)
                                .addComponent(targetCurrencyLabel)
                                .addComponent(resultLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(swingMoneyDialog)
                                        .addComponent(initialCurrencyDialog))
                                .addComponent(targetCurrencyDialog)
                                .addComponent(display))
        );


        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(initialCurrencyLabel)
                                .addComponent(swingMoneyDialog)
                                .addComponent(initialCurrencyDialog))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(targetCurrencyLabel)
                                .addComponent(targetCurrencyDialog))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(resultLabel)
                                .addComponent(display))
        );

        return panel;
    }


    private JPanel createButtonPanel(Color backgroundColor) {
        JPanel panel = new JPanel();
        panel.setBackground(backgroundColor);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(e -> commands.get("exchange money").execute());
        panel.add(calculateButton);

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> clearInputs());
        panel.add(clearButton);

        return panel;
    }

    private void clearInputs() {

        if (moneyDialog instanceof SwingMoneyDialog swingMoneyDialog) {
            for (Component component : swingMoneyDialog.getComponents()) {
                if (component instanceof JTextField) {
                    ((JTextField) component).setText("");
                }
            }
        }

        if (currencyDialog instanceof SwingCurrencyDialog swingCurrencyDialog) {
            for (Component component : swingCurrencyDialog.getComponents()) {
                if (component instanceof JComboBox) {
                    ((JComboBox<?>) component).setSelectedIndex(0);
                }
            }
        }


        if (moneyDisplay instanceof SwingMoneyDisplay swingMoneyDisplay) {
            for (Component component : swingMoneyDisplay.getComponents()) {
                if (component instanceof JLabel) {
                    ((JLabel) component).setText("");
                }
            }
        }
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


