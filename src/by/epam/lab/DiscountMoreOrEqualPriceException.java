package by.epam.lab;

public class DiscountMoreOrEqualPriceException extends IllegalArgumentException  {

    public DiscountMoreOrEqualPriceException() {
    }

    public DiscountMoreOrEqualPriceException(String s) {
        super(s);
    }

    public DiscountMoreOrEqualPriceException(String message, Throwable cause) {
        super(message, cause);
    }

    public DiscountMoreOrEqualPriceException(Throwable cause) {
        super(cause);
    }
}
