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
                List<Purchase> list = dayPurchaseMap.get(dayOfWeek);
                if (list == null) {
                    dayPurchaseMap.put(dayOfWeek, list = new ArrayList<>());
                }
                list.add(purchase);
                if (purchase.getClass() == PricePurchase.class) {
                    priceDiscountPurchases.add((PricePurchase) purchase);
                }
            }
            printMap(lastPurchaseMap, LAST_PURCHASE_MAP);
            printMap(firstPurchaseMap, FIRST_PURCHASE_MAP);

            Purchase reqPurchase = new Purchase("bread",
                    new Byn(155), 0);
            findAndShow(firstPurchaseMap, reqPurchase, FIRST_PURCHASE_MAP);
            findAndShow(lastPurchaseMap, reqPurchase, LAST_PURCHASE_MAP);
            findAndShow(firstPurchaseMap, new Purchase("bread",
                    new Byn(170), 0), FIRST_PURCHASE_MAP);

            removeEntries(lastPurchaseMap, new EntryChecker<Purchase, DayOfWeek>() {
                @Override
                public boolean check(Map.Entry<Purchase, DayOfWeek> entry) {
                    return MEAT.equals(entry.getKey().getProductName());
                }
            });
            removeEntries(firstPurchaseMap, new EntryChecker<Purchase,
                    DayOfWeek>() {
                @Override
                public boolean check(Map.Entry<Purchase, DayOfWeek> entry) {
                    return entry.getValue() == DayOfWeek.FRIDAY;
                }
            });

            printMap(firstPurchaseMap, FIRST_PURCHASE_MAP);
            printMap(lastPurchaseMap, LAST_PURCHASE_MAP);
            printTotalCost(priceDiscountPurchases);
            printMap(dayPurchaseMap, ENUM_MAP);

            for (Map.Entry<DayOfWeek, List<Purchase>> entry :
                    dayPurchaseMap.entrySet()) {
                System.out.print(entry.getKey() + TOTAL_COST);
                printTotalCost(entry.getValue());
            }

            removeEntries(dayPurchaseMap, new EntryChecker<DayOfWeek,
                    List<Purchase>>() {
                @Override
                public boolean check(Map.Entry<DayOfWeek, List<Purchase>> entry) {
                    boolean purchaseWithNameMilk = false;
                    for (Purchase purchase : entry.getValue()) {
                        if (MILK.equals(purchase.getProductName())) {
                            purchaseWithNameMilk = true;
                            break;
                        }
                    }
                    return purchaseWithNameMilk;
                }
            });
            printMap(dayPurchaseMap, ENUM_MAP);
        } catch (FileNotFoundException e) {
            System.out.println(NO_FILE);
        }
    }

    private static <K, V> void printMap(Map<K, V> purchaseByDayOfWeek,
                                        String message) {
        System.out.println(message);
        for (Map.Entry<K, V> entry : purchaseByDayOfWeek.entrySet()) {
            System.out.println(entry.getKey() + IN_DELIMITER + entry.getValue());
        }
    }

    private static <K, V> void findAndShow(Map<K, V> purchaseByDayOfWeek, K searchKey
            , String header) {
        System.out.println(header);
        V messageResult = purchaseByDayOfWeek.get(searchKey);
        System.out.println(messageResult == null ? NOT_FOUND : messageResult);
    }

    private static <K, V> void removeEntries(Map<K, V> map,
                                             EntryChecker<K, V> entryChecker) {
        for (Iterator<Map.Entry<K, V>> it = map.entrySet().iterator(); it.hasNext(); ) {
            if (entryChecker.check(it.next())) {
                it.remove();
            }
        }
    }

    private static void printTotalCost
            (List<? extends Purchase> priceDiscountPurchases) {
        Byn totalCost = new Byn();
        for (Purchase purchase : priceDiscountPurchases) {
            totalCost = totalCost.add(purchase.getCost());
        }
        System.out.println(totalCost);
    }

    private interface EntryChecker<K, V> {
        boolean check(Map.Entry<K, V> entry);

    }
}
