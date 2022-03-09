package by.epam.lab;

import java.util.Scanner;

public class PurchaseFactory {

    private static enum PurchaseKind {
        GENERAL_PURCHASE, VALUE_DISCOUNT_PURCHASE, RATIO_DISCOUNT_PURCHASE;
    }

    public static Purchase getPurchaseFromFactory(Scanner sc) {
        String id = sc.next();
        PurchaseKind kind = PurchaseKind.valueOf(id);
        switch (kind) {
            case GENERAL_PURCHASE:
                return new Purchase(sc.next(), sc.nextInt(), sc.nextInt());
            case VALUE_DISCOUNT_PURCHASE:
                return new ValueDiscountPurchase(sc.next(), sc.nextInt(), sc.nextInt(), sc.nextInt());
            case RATIO_DISCOUNT_PURCHASE:
                return new RatioDiscountPurchase(sc.next(), sc.nextInt(), sc.nextInt(), sc.nextDouble());
            default:
                throw new IllegalArgumentException();
        }
    }

}
