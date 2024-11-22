package software.ulpgc.moneycalculator.apps.windows.control;

import software.ulpgc.moneycalculator.apps.windows.model.Currency;
import software.ulpgc.moneycalculator.apps.windows.model.ExchangeRate;
import software.ulpgc.moneycalculator.apps.windows.model.Money;
import software.ulpgc.moneycalculator.apps.windows.loader.ExchangeRateLoader;
import software.ulpgc.moneycalculator.apps.windows.view.CurrencyDialog;
import software.ulpgc.moneycalculator.apps.windows.view.MoneyDialog;
import software.ulpgc.moneycalculator.apps.windows.view.MoneyDisplay;

import java.text.DecimalFormat;
import java.time.LocalDate;

public class ExchangeCommand implements Command {
    private final MoneyDialog moneyDialog;
    private final CurrencyDialog currencyDialog;
    private final ExchangeRateLoader loader;
    private final MoneyDisplay moneyDisplay;
    private static final DecimalFormat format = new DecimalFormat("#.###");

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
        Money result = new Money(twoDecimalDouble(exchangeRate, money), currency);
        moneyDisplay.show(result);
    }

    private double twoDecimalDouble(ExchangeRate exchangeRate, Money money){

        String stringAmount = format.format(money.amount() * exchangeRate.rate());
        return Double.parseDouble(stringAmount.replace(',','.'));
    }
}
