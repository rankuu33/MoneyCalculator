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

    /*private final CurrencyDialog currencyDialog;
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
    }*/


    private CurrencyDialog currencyDialog;
    private MoneyDialog moneyDialog;
    private MoneyDisplay moneyDisplay;
    private final Map<String, Command> commands = new HashMap<>();

    public SwingMainFrame() throws HeadlessException {

        setTitle("Money Calculator");
        setSize(600, 300); // Tamaño compacto
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

        // Vertical Group: Alinear filas con sus respectivos componentes
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
        // Limpiar los valores de entrada en SwingMoneyDialog
        if (moneyDialog instanceof SwingMoneyDialog) {
            SwingMoneyDialog swingMoneyDialog = (SwingMoneyDialog) moneyDialog;
            for (Component component : swingMoneyDialog.getComponents()) {
                if (component instanceof JTextField) {
                    ((JTextField) component).setText("");
                }
            }
        }

        // Limpiar los valores de selección en SwingCurrencyDialog
        if (currencyDialog instanceof SwingCurrencyDialog) {
            SwingCurrencyDialog swingCurrencyDialog = (SwingCurrencyDialog) currencyDialog;
            for (Component component : swingCurrencyDialog.getComponents()) {
                if (component instanceof JComboBox) {
                    ((JComboBox<?>) component).setSelectedIndex(0);
                }
            }
        }

        // Limpiar el contenido del MoneyDisplay
        if (moneyDisplay instanceof SwingMoneyDisplay) {
            SwingMoneyDisplay swingMoneyDisplay = (SwingMoneyDisplay) moneyDisplay;
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


