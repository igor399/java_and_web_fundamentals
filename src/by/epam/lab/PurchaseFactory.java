package by.epam.lab;

import java.util.Scanner;

public class PurchaseFactory {

    private enum PurchaseKind {

        GENERAL_PURCHASE {
            Purchase getPurchase(Scanner sc) {
                return new Purchase(sc);
            }
        }, PRICE_DISCOUNT_PURCHASE {
            Purchase getPurchase(Scanner sc) {
                return new PriceDiscountPurchase(sc);
            }
        }, PERCENT_DISCOUNT_PURCHASE {
            Purchase getPurchase(Scanner sc) {
                return new PercentDiscountPurchase(sc);
            }
        };

        abstract Purchase getPurchase(Scanner sc);
    }

    public static Purchase getPurchaseFromFactory(Scanner sc) {
        return PurchaseKind.valueOf(sc.next()).getPurchase(sc);
    }

}

