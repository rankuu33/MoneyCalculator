package software.ulpgc.moneycalculator.apps.windows.model;


import java.time.LocalDate;
import java.util.Date;

public record ExchangeRate(Currency from, Currency to, double rate, LocalDate date) {
}
