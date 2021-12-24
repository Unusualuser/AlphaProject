package test.alpha.AlphaProject.exceptions;

public class IncorrectCurrencyCodeException extends Exception {
    public IncorrectCurrencyCodeException() {
        super("Incorrect currencyCode");
    }
}
