import by.epam.lab.beans.*;
import by.epam.lab.services.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static by.epam.lab.services.GlobalConstants.*;

public class Runner {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new FileReader(FILE_NAME))) {
            Map<Purchase, DayOfWeek> firstPurchaseMap = new HashMap<>();
            Map<Purchase, DayOfWeek> lastPurchaseMap = new HashMap<>();
            Map<DayOfWeek, List<Purchase>> dayPurchaseMap = new EnumMap<>
                    (DayOfWeek.class);
            List<PricePurchase> priceDiscountPurchases = new ArrayList<>();
            while (sc.hasNext()) {
                Purchase purchase = PurchaseFactory.
                        getPurchaseFromFactory(sc.nextLine());
                DayOfWeek dayOfWeek = DayOfWeek.valueOf(sc.nextLine());

                lastPurchaseMap.put(purchase, dayOfWeek);
                if (!firstPurchaseMap.containsKey(purchase)) {
                    firstPurchaseMap.put(purchase, dayOfWeek);
                }

                if (purchase.getClass() == PricePurchase.class) {
                    priceDiscountPurchases.add((PricePurchase) purchase);
                }
                if (!dayPurchaseMap.containsKey(dayOfWeek)) {
                    dayPurchaseMap.put(dayOfWeek, new ArrayList<>());
                }
                dayPurchaseMap.get(dayOfWeek).add(purchase);
            }
            System.out.println(FIRST_PURCHASE_MAP);
            print(firstPurchaseMap);
            System.out.println(LAST_PURCHASE_MAP);
            print(lastPurchaseMap);

            Purchase reqPurchase = new Purchase("bread",
                    new Byn(155), 0);
            findPurchase(reqPurchase, null, firstPurchaseMap);
            findPurchase(reqPurchase, null, lastPurchaseMap);
            findPurchase(new Purchase("bread",
                    new Byn(170), 0), null, firstPurchaseMap);
            removeAllEntries(new Purchase("meat", null, 0),
                    null, lastPurchaseMap);
            removeAllEntries(null, DayOfWeek.FRIDAY, firstPurchaseMap);

            System.out.println(FIRST_PURCHASE_MAP);
            print(firstPurchaseMap);
            System.out.println(LAST_PURCHASE_MAP);
            print(lastPurchaseMap);

            printTotalCost(priceDiscountPurchases);

            System.out.println(ENUM_MAP);
            print(dayPurchaseMap);

            for (Map.Entry<DayOfWeek, List<Purchase>> entry :
                    dayPurchaseMap.entrySet()) {
                System.out.print(entry.getKey() + TOTAL_COST);
                printTotalCost(entry.getValue());
            }

            findPurchase(null, DayOfWeek.MONDAY, dayPurchaseMap);
        } catch (FileNotFoundException e) {
            System.out.println(NO_FILE);
        }
    }

    private static void print(Map<?, ?> purchaseByDayOfWeek) {
        for (Map.Entry<?, ?> entry : purchaseByDayOfWeek.entrySet()) {
            System.out.println(entry.getKey() + IN_DELIMITER + entry.getValue());
        }
    }

    private static void findPurchase(Purchase purchase, DayOfWeek dayOfWeek
            , Map<?, ?> purchaseByDayOfWeek) {
        if (purchase != null) {
            if (purchaseByDayOfWeek.containsKey(purchase)) {
                System.out.println(purchaseByDayOfWeek.get(purchase));
            } else {
                System.out.println(NOT_FOUND);
            }
        }
        if (dayOfWeek != null) {
            if (purchaseByDayOfWeek.containsKey(dayOfWeek)) {
                System.out.println(purchaseByDayOfWeek.get(dayOfWeek));
            } else {
                System.out.println(NOT_FOUND);
            }
        }
    }

    private static void removeAllEntries(Purchase purchase, DayOfWeek dayOfWeek
            , Map<Purchase, DayOfWeek> purchaseByDayOfWeek) {
        Iterator<Map.Entry<Purchase, DayOfWeek>> it =
                purchaseByDayOfWeek.entrySet().iterator();
        if (purchase != null) {
            while (it.hasNext()) {
                if (it.next().getKey().getProductName().
                        equals(purchase.getProductName())) {
                    it.remove();
                }
            }
        }
        if (dayOfWeek != null) {
            while (it.hasNext()) {
                if (it.next().getValue() == dayOfWeek) {
                    it.remove();
                }
            }
        }
    }

    private static <T extends Purchase> void printTotalCost
            (List<T> priceDiscountPurchases) {
        Byn totalCost = new Byn();
        for (Purchase purchase : priceDiscountPurchases) {
            totalCost = totalCost.add(purchase.getCost());
        }
        System.out.println(totalCost);
    }
}
