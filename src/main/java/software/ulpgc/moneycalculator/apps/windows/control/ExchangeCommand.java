package software.ulpgc.moneycalculator.apps.windows.control;

import software.ulpgc.moneycalculator.apps.windows.model.Currency;
import software.ulpgc.moneycalculator.apps.windows.model.ExchangeRate;
import software.ulpgc.moneycalculator.apps.windows.model.Money;
import software.ulpgc.moneycalculator.apps.windows.loader.ExchangeRateLoader;
import software.ulpgc.moneycalculator.apps.windows.view.CurrencyDialog;
import software.ulpgc.moneycalculator.apps.windows.view.MoneyDialog;
import software.ulpgc.moneycalculator.apps.windows.view.MoneyDisplay;

import java.time.LocalDate;

public class ExchangeCommand implements Command {
    private final MoneyDialog moneyDialog;
    private final CurrencyDialog currencyDialog;
    private final ExchangeRateLoader loader;
    private final MoneyDisplay moneyDisplay;

    public ExchangeCommand(MoneyDialog moneyDialog, CurrencyDialog currencyDialog, ExchangeRateLoader loader, MoneyDisplay moneyDisplay) {
        this.moneyDialog = moneyDialog;
        this.currencyDialog = currencyDialog;
        this.loader = loader;
        this.moneyDisplay = moneyDisplay;
    }

    @Override
    public void execute() {
        Money money = moneyDialog.get();
        Currency currency = currencyDialog.get();
        ExchangeRate exchangeRate = loader.load(money.currency(), currency, LocalDate.now());
        Money result = new Money(money.amount() * exchangeRate.rate(), currency);

        moneyDisplay.show(result);
    }
}
