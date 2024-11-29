package software.ulpgc.moneycalculator.apps.windows;

import software.ulpgc.moneycalculator.apps.windows.control.Command;
import software.ulpgc.moneycalculator.apps.windows.control.ExchangeCommand;
import software.ulpgc.moneycalculator.apps.windows.loader.FixerCurrencyLoader;
import software.ulpgc.moneycalculator.apps.windows.loader.FixerExchangeRateLoader;
import software.ulpgc.moneycalculator.apps.windows.model.Currency;
import software.ulpgc.moneycalculator.apps.windows.model.Rate;
import software.ulpgc.moneycalculator.apps.windows.view.SwingMainFrame;

import java.util.List;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        SwingMainFrame frame = new SwingMainFrame();
        List<Currency> currencies = new FixerCurrencyLoader().load();
        Map<String, Double> exchangeRateLoader = new FixerExchangeRateLoader().load();
        Command command = new ExchangeCommand(
                frame.moneyDialog().define(currencies),
                frame.currencyDialog().define(currencies),
                new Rate(exchangeRateLoader),
                frame.moneyDisplay()
        );
        frame.add("exchange money", command);
        frame.setVisible(true);

    }
}
