package software.ulpgc.moneycalculator.apps.windows.model;

import software.ulpgc.moneycalculator.apps.windows.loader.ExchangeRateLoader;

import java.time.LocalDate;

public class Rate implements ExchangeRateLoader {

    @Override
    public ExchangeRate load(Currency from, Currency to, LocalDate date) {
        return null;
    }
}
