import by.epam.lab.beans.*;
import by.epam.lab.services.PurchaseUtils;

public class Runner {
    public static void main(String[] args) {
        Purchase<Priceable, Integer> p1 = new Purchase<>(new Product("milk",
                new Byn(170)), 20);

        PurchaseUtils<Priceable, Integer> pu1 = new PurchaseUtils<>(p1);
        pu1.printPurchase();
        pu1.printCost();
        Purchase<Priceable, Double> p2 = new Purchase<>(new Product("sugar",
                new Byn(300)), 12.5);

        PurchaseUtils<Priceable, Double> pu2 = new PurchaseUtils<>(p2);
        pu2.printCost();
        pu2.printCostDiff(p1);
        Purchase<Priceable, Double> p3 = new Purchase<>(new DiscountProduct("sugar",
                new Byn(260), new Byn(10)), 60.0);

        PurchaseUtils<Service, Double> pu4 = new PurchaseUtils<>(new Purchase<>
                (new Service("gym workout", new Byn(7560), 5), 2.25));

        Service service = pu4.getPurchase().getItem();
        System.out.println(service);
        pu4.printCost();
        pu2.printSameCost(p1, p3, pu4.getPurchase());
    }
}
