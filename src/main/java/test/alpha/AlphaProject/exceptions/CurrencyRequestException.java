package test.alpha.AlphaProject.exceptions;

public class CurrencyRequestException extends Exception {
    public CurrencyRequestException() {
        super("CurrencyRequest failed");
    }
}
