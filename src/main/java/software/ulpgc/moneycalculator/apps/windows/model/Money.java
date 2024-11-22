package software.ulpgc.moneycalculator.apps.windows.model;

public record Money(double amount, Currency currency) {

    @Override
    public String toString() {
        return "amount=" + amount +
                ", currency=" + currency;
    }
}
