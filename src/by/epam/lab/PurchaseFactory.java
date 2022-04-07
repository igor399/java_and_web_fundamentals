package by.epam.lab;

public class PurchaseFactory {
    private final static String SEMICOLON = ";";
    private final static int PARAM_NUMB = 3;

    private enum PurchaseKind {
        GENERAL_PURCHASE {
            @Override
            Purchase getPurchase(String[] param) {
                return new Purchase(param);
            }
        },
        PRICE_DISCOUNT_PURCHASE {
            @Override
            Purchase getPurchase(String[] param) {
                return new PriceDiscountPurchase(param);
            }
        };

        abstract Purchase getPurchase(String[] param);
    }

    public static Purchase getPurchaseFromFactory(String csvLine) throws LineException {
        String[] param = csvLine.split(SEMICOLON);
        try {
            return getPurchaseKind(param.length).getPurchase(param);
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            throw new LineException(csvLine);
        }
    }

    private static PurchaseKind getPurchaseKind(int paramLength) {
        return PurchaseKind.values()[paramLength - PARAM_NUMB];
    }
}
