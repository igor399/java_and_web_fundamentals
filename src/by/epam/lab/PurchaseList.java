package by.epam.lab;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static by.epam.lab.PurchaseFactory.getPurchaseFromFactory;

public class PurchaseList {
    private final static String NO_FILE = "File not found";
    private final static String NEXT_LINE = "\n";

    private final List<Purchase> purchases = new ArrayList<>();
    private final Comparator<Purchase> comparator;

    public PurchaseList() {
        this("", new PurchaseComparator());
    }

    public PurchaseList(String fileName, Comparator<Purchase> comparator) {
        this.comparator = comparator;
        try (Scanner sc = new Scanner(new FileReader(fileName))) {
            while (sc.hasNext()) {
                try {
                    purchases.add(getPurchaseFromFactory(sc.nextLine()));
                } catch (LineException e) {
                    System.err.println(e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println(NO_FILE);
        }
    }

    public List<Purchase> getPurchases() {
        List<Purchase> copyList = new ArrayList<>();
        for (Purchase purchase : purchases) {
            if (purchase.getClass() == Purchase.class) {
                copyList.add(new Purchase(purchase));
            } else {
                copyList.add(new PriceDiscountPurchase((PriceDiscountPurchase) purchase));
            }
        }
        return copyList;
    }

    public void add(Purchase purchase) {
        purchases.add(purchase);

    }

    public void addArray(Purchase[] purchaseArray) {
        purchases.addAll(Arrays.asList(purchaseArray));
    }

    public void insert(int index, Purchase purchase) {
        if (index < 0) {
            purchases.add(0, purchase);
        } else if (index >= purchases.size()) {
            purchases.add(purchase);
        } else {
            purchases.add(index, purchase);
        }
    }

    public void remove(int startInd, int endInd) {
        if ((startInd < 0 && endInd < 0) || (startInd > purchases.size() && endInd > purchases.size())
                || startInd == endInd) {
            throw new IllegalArgumentException();
        }
        if (startInd < 0) {
            startInd = 0;
        }
        if (startInd > endInd) {
            int temp = startInd;
            startInd = endInd;
            endInd = temp;
        }
        if (endInd > purchases.size()) {
            endInd = purchases.size();
        }
        purchases.subList(startInd, endInd).clear();
    }

    public Byn getTotalCost() {
        Byn totalCost = new Byn(0);
        for (Purchase purchase : getPurchases()) {
            totalCost = totalCost.add(purchase.getCost());
        }
        return totalCost;
    }

    public void sort() {
        Collections.sort(purchases, comparator);
    }

    public Purchase binarySearch(String strPurchase) throws LineException {
        Purchase initPurchase = null;
        Purchase tmpPurchase = PurchaseFactory.getPurchaseFromFactory(strPurchase);
        this.sort();
        int index = Collections.binarySearch(purchases, tmpPurchase, comparator);
        if (index >= 0) {
            initPurchase = purchases.get(index);
        }
        return initPurchase;
    }

    @Override
    public String toString() {
        StringBuilder purchaseListStr = new StringBuilder();
        for (Purchase purchase : purchases) {
            purchaseListStr.append(purchase).append(NEXT_LINE);
        }
        return purchaseListStr.toString();
    }
}
