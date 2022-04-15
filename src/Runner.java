import by.epam.lab.beans.*;
import by.epam.lab.services.PurchaseUtils;

public class Runner {
    public static void main(String[] args) {
        Purchase p1 = new Purchase(new Product("milk", new Byn(170)), 20);
        PurchaseUtils pu1 = new PurchaseUtils(p1);
        pu1.printPurchase();
        pu1.printCost();
        Purchase p2 = new Purchase(new Product("sugar", new Byn(300)), 12.5);
        PurchaseUtils pu2 = new PurchaseUtils(p2);
        pu2.printCost();
        pu2.printCostDiff(p1);
        Purchase p3 = new Purchase(new DiscountProduct("sugar", new Byn(260),
                new Byn(10)), 60);
        PurchaseUtils pu4 = new PurchaseUtils(new Purchase(new Service("gym workout",
                new Byn(7560), 5), 2.25));
        Item workoutGym = pu4.getPurchase().getItem();
        System.out.println(workoutGym);
        pu4.printCost();
        pu2.printSameCost(new Purchase[]{p1, p3, pu4.getPurchase()});
    }
}
