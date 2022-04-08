package by.epam.lab.comparator;

import by.epam.lab.bean.Purchase;

import java.util.Comparator;

public class PurchaseComparator implements Comparator<Purchase> {
    public int compare(Purchase o1, Purchase o2) {
        return o1.toString().compareTo(o2.toString());
    }
}
