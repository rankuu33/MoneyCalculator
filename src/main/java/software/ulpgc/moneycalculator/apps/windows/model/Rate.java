package software.ulpgc.moneycalculator.apps.windows.model;

import software.ulpgc.moneycalculator.apps.windows.loader.ExchangeRateLoader;

import java.time.LocalDate;
import java.util.Map;

public class Rate implements ExchangeRateLoader {

    Map<String, Double> rates;

    public Rate(Map<String, Double> rates) {
        this.rates = rates;
    }

    @Override
    public ExchangeRate load(Currency from, Currency to, LocalDate date) {
        if (from.code().equals(to.code())) {
            return new ExchangeRate(from, to, 1, date);
        }
        double rate = (1/rates.get(from.code())) / (1/rates.get(to.code()));
        return new ExchangeRate(from, to, rate, date );
    }

}
