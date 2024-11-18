package software.ulpgc.moneycalculator.apps.windows.model;

import java.time.LocalDate;

public record ExchangeRate(Currency from, Currency to, double rate, LocalDate date) {
}
