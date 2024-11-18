package software.ulpgc.moneycalculator.apps.windows;


import software.ulpgc.moneycalculator.apps.windows.loader.FixerExchangeRateLoader;
import software.ulpgc.moneycalculator.apps.windows.loader.FixerCurrencyLoader;
import software.ulpgc.moneycalculator.apps.windows.control.ExchangeCommand;
import software.ulpgc.moneycalculator.apps.windows.model.Currency;
import software.ulpgc.moneycalculator.apps.windows.view.SwingMainFrame;


import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Currency> currencies = new FixerCurrencyLoader().load();
        SwingMainFrame mainFrame = new SwingMainFrame(currencies);
        /*ExchangeCommand exchangeCommand = new ExchangeCommand(
                mainFrame.moneyDialog(),
                mainFrame.currencyDialog(),
                new FixerExchangeRateLoader(),
                mainFrame.moneyDisplay()
        );
        mainFrame.add("exchange", exchangeCommand);
        mainFrame.setVisible(true);*/
    }
}
