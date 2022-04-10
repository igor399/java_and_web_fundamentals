package by.epam.lab.services;

import by.epam.lab.beans.Byn;
import by.epam.lab.beans.PriceDiscountPurchase;
import by.epam.lab.beans.Purchase;
import by.epam.lab.exceptions.LineException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static by.epam.lab.services.PurchaseFactory.getPurchaseFromFactory;

public class PurchaseList {
    private final static String NO_FILE = "File not found";
    private final static String NEXT_LINE = "\n";
    private List<Purchase> purchases = new ArrayList<>();
    private final Comparator<Purchase> comparator;
    private boolean isSorted = false;

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
            purchases = new ArrayList<>();
            isSorted = true;
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
        isSorted = false;
    }

    public Purchase getIndPurchases(int index) {
        return purchases.get(index);
    }

    public void addArray(Purchase[] purchaseArray) {
        purchases.addAll(Arrays.asList(purchaseArray));
        isSorted = false;
    }

    public void insert(int index, Purchase purchase) {
        if (index < 0) {
            purchases.add(0, purchase);
        } else if (index >= purchases.size()) {
            purchases.add(purchase);
        } else {
            purchases.add(index, purchase);
        }
        isSorted = false;
    }

    public int remove(int startInd, int endInd) {
        int startSize = purchases.size();
        if (startInd > endInd) {
            return 0;
        }
        startInd = Math.max(startInd, 0);
        endInd = Math.min(endInd, purchases.size());
        purchases.subList(startInd, endInd).clear();
        isSorted = false;
        return startSize - purchases.size();
    }

    public Byn getTotalCost() {
        Byn totalCost = new Byn(0);
        for (Purchase purchase : getPurchases()) {
            totalCost = totalCost.add(purchase.getCost());
        }
        return totalCost;
    }

    public void sort() {
        if (!isSorted) {
            Collections.sort(purchases, comparator);
        }
        isSorted = true;
    }

    public int binarySearch(Purchase purchase) {
        if (!isSorted) {
            purchases.sort(comparator);
        }
        return Collections.binarySearch(purchases, purchase, comparator);
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
