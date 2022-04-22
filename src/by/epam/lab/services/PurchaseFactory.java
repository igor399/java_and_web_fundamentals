package by.epam.lab.services;

import by.epam.lab.beans.*;

import static by.epam.lab.services.GlobalConstants.*;

public class PurchaseFactory {
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
                return new PricePurchase(param);
            }
        };

        abstract Purchase getPurchase(String[] param);
    }

    public static Purchase getPurchaseFromFactory(String csvLine) {
        String[] param = csvLine.split(SEMICOLON);
        return getPurchaseKind(param.length).getPurchase(param);
    }

    private static PurchaseKind getPurchaseKind(int paramLength) {
        return PurchaseKind.values()[paramLength - PARAM_NUMB];
    }
}
