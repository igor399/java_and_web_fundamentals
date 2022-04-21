package by.epam.lab.services;

import by.epam.lab.beans.*;
import by.epam.lab.exceptions.*;

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
