import by.epam.lab.beans.*;
import by.epam.lab.services.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static by.epam.lab.services.GlobalConstants.*;

public class Runner {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new FileReader(FILE_NAME))) {
            Map<Purchase, DayOfWeek> purchaseByFirstDayWeekDay = new HashMap<>();
            Map<Purchase, DayOfWeek> purchaseByLastDayWeekDay = new HashMap<>();
            List<PricePurchase> pricePurchases = new ArrayList<>();
            Map<DayOfWeek, List<Purchase>> dayOfWeekByPurchases =
                    new EnumMap<>(DayOfWeek.class);
            while (sc.hasNext()) {
                Purchase purchase =
                        PurchaseFactory.getPurchaseFromFactory(sc.nextLine());
                DayOfWeek dayOfWeek = DayOfWeek.valueOf(sc.nextLine());
                purchaseByLastDayWeekDay.put(purchase, dayOfWeek);
                if (!purchaseByFirstDayWeekDay.containsKey(purchase)) {
                    purchaseByFirstDayWeekDay.put(purchase, dayOfWeek);
                }
                if (purchase.getClass() == PricePurchase.class) {
                    pricePurchases.add((PricePurchase) purchase);
                }
                if (!dayOfWeekByPurchases.containsKey(dayOfWeek)) {
                    dayOfWeekByPurchases.put(dayOfWeek, new ArrayList<>());
                }
                dayOfWeekByPurchases.get(dayOfWeek).add(purchase);
            }
            System.out.println(LAST_PURCHASE_MAP);
            print(purchaseByLastDayWeekDay);

            System.out.println(FIRST_PURCHASE_MAP);
            print(purchaseByFirstDayWeekDay);

            Purchase expPurchase = new Purchase("bread", new Byn(155), 0);
            findPurchase(expPurchase, null, purchaseByFirstDayWeekDay);
            findPurchase(expPurchase, null, purchaseByLastDayWeekDay);
            findPurchase(new Purchase("bread", new Byn(170), 0), null, purchaseByFirstDayWeekDay);

            removeAllEntries(new Purchase("meat", null, 0), null, purchaseByLastDayWeekDay);
            removeAllEntries(null, DayOfWeek.FRIDAY, purchaseByFirstDayWeekDay);

            System.out.println(FIRST_PURCHASE_MAP);
            print(purchaseByFirstDayWeekDay);

            System.out.println(LAST_PURCHASE_MAP);
            print(purchaseByLastDayWeekDay);

            printTotalListCost(pricePurchases);

            System.out.println(ENUM_MAP);
            print(dayOfWeekByPurchases);

            for (Map.Entry<DayOfWeek, List<Purchase>> entry :
                    dayOfWeekByPurchases.entrySet()) {
                System.out.print(entry.getKey() + TOTAL_COST);
                printTotalListCost(entry.getValue());
            }

            findPurchase(null, DayOfWeek.MONDAY, dayOfWeekByPurchases);

        } catch (FileNotFoundException e) {
            System.err.println(NO_FILE);
        }
    }

    private static <K, V> void print(Map<K, V> map) {
        Set<K> keySet = map.keySet();
        for (K key : keySet) {
            System.out.println(key + IN_DELIMITER + map.get(key));

        }
    }

    private static void findPurchase(Purchase purchase, DayOfWeek dayOfWeek,
                                     Map<?, ?> purchaseByDayOfWeek) {
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

    private static void removeAllEntries(Purchase purchase, DayOfWeek dayOfWeek,
                                         Map<Purchase, DayOfWeek> purchaseByDayOfWeek) {
        Iterator<Map.Entry<Purchase, DayOfWeek>> iterator =
                purchaseByDayOfWeek.entrySet().iterator();
        if (purchase != null) {
            while (iterator.hasNext()) {
                if (iterator.next().getKey().getProductName().
                        equals(purchase.getProductName())) {
                    iterator.remove();
                }
            }
        }
        if (dayOfWeek != null) {
            while (iterator.hasNext()) {
                if (iterator.next().getValue() == dayOfWeek) {
                    iterator.remove();
                }
            }
        }
    }

    private static <P extends Purchase> void printTotalListCost
            (List<P> pricePurchases) {
        Byn totalCost = new Byn();
        for (Purchase purchase : pricePurchases) {
            totalCost = totalCost.add(purchase.getCost());
        }
        System.out.println(totalCost);
    }
}



