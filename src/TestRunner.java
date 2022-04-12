import by.epam.lab.beans.*;
import by.epam.lab.exceptions.*;
import by.epam.lab.services.*;

import org.junit.Assert;
import org.junit.Test;

public class TestRunner {
    private static final String FILE_NAME = "resources/in.csv";
    private final Purchase[] PURCHASE_ARRAY = {
            new Purchase("bread", new Byn(145), 5),
            new Purchase("bread", new Byn(154), 3),
            new PriceDiscountPurchase("bread", new Byn(155), 1, new Byn(2)),
            new PriceDiscountPurchase("butter", new Byn(341), 1, new Byn(1)),
            new Purchase("butter", new Byn(370), 1),
            new PriceDiscountPurchase("meat", new Byn(1100), 2, new Byn(80)),
            new Purchase("milk", new Byn(131), 2),
            new PriceDiscountPurchase("potato", new Byn(180), 2, new Byn(10))
    };

    @Test(expected = LineException.class)
    public void testPurchaseFactoryWrongPriceValue() throws LineException {
        Purchase purchase = PurchaseFactory.getPurchaseFromFactory("candy;0;2");
    }

    @Test(expected = LineException.class)
    public void testPurchaseFactoryWrongPriceNumUnits() throws LineException {
        Purchase purchase = PurchaseFactory.getPurchaseFromFactory("candy;-100;-2");
    }

    @Test(expected = LineException.class)
    public void testPurchaseFactoryWrongDiscount() throws LineException {
        Purchase purchase = PurchaseFactory.getPurchaseFromFactory("candy;100;2;0");
    }

    @Test(expected = LineException.class)
    public void testPurchaseFactoryWrongParamPriceNumUnits() throws LineException {
        Purchase purchase = PurchaseFactory.getPurchaseFromFactory("candy");
    }

    @Test(expected = LineException.class)
    public void testPurchaseFactoryWrongProductName() throws LineException {
        Purchase purchase = PurchaseFactory.getPurchaseFromFactory(";100;2");
    }

    @Test(expected = LineException.class)
    public void testPurchaseFactoryWrongSpaceProductName() throws LineException {
        Purchase purchase = PurchaseFactory.getPurchaseFromFactory(" ;100;2");
    }

    @Test(expected = LineException.class)
    public void testPurchaseFactoryWrongPrice() throws LineException {
        Purchase purchase = PurchaseFactory.getPurchaseFromFactory("beer;;1");
    }

    @Test(expected = LineException.class)
    public void testPurchaseFactoryWrongNumUnits() throws LineException {
        Purchase purchase = PurchaseFactory.getPurchaseFromFactory("beer;100;");
    }

    @Test(expected = LineException.class)
    public void testPurchaseFactoryWrongProductNamePrice() throws LineException {
        Purchase purchase = PurchaseFactory.getPurchaseFromFactory(";;3");
    }

    @Test(expected = LineException.class)
    public void testPurchaseFactoryWrongParam() throws LineException {
        Purchase purchase = PurchaseFactory.getPurchaseFromFactory(";;");
    }

    @Test(expected = LineException.class)
    public void testPurchaseFactoryWrongDiscountMorePrice() throws LineException {
        Purchase purchase = PurchaseFactory.getPurchaseFromFactory("candy;100;2;500");
    }

    @Test(expected = LineException.class)
    public void testPurchaseFactoryWrongDiscountEqualPrice() throws LineException {
        Purchase purchase = PurchaseFactory.getPurchaseFromFactory("candy;100;2;100");
    }

    @Test(expected = LineException.class)
    public void testPurchaseFactoryOutOfBoundsParam() throws LineException {
        Purchase purchase = PurchaseFactory.getPurchaseFromFactory("water;15;4;0.1;cold");
    }

    @Test(expected = LineException.class)
    public void testPurchaseFactoryWrongDiscountNegative() throws LineException {
        Purchase purchase = PurchaseFactory.getPurchaseFromFactory("water;70;5;-1");
    }

    @Test(expected = LineException.class)
    public void testPurchaseFactoryWrongPriceNegative() throws LineException {
        Purchase purchase = PurchaseFactory.getPurchaseFromFactory("water;-70;5;8");
    }

    @Test(expected = LineException.class)
    public void testPurchaseFactoryWrongPriceDiscountNegative() throws LineException {
        Purchase purchase = PurchaseFactory.getPurchaseFromFactory("banana;-70;5;-8");
    }

    @Test(expected = LineException.class)
    public void testPurchaseFactoryWrongPriceNumUnitsDiscountNegative() throws LineException {
        Purchase purchase = PurchaseFactory.getPurchaseFromFactory("apple;-70;-10;-8");
    }

    @Test(expected = LineException.class)
    public void testPurchaseFactoryWrongDiscountMoreThanPrice() throws LineException {
        Purchase purchase = PurchaseFactory.getPurchaseFromFactory("apple;23;3;24");
    }

    @Test(expected = LineException.class)
    public void testPurchaseFactoryWrongPriceNumUnitsNegative() throws LineException {
        Purchase purchase = PurchaseFactory.getPurchaseFromFactory("apple;-120;-10");
    }

    @Test(expected = LineException.class)
    public void testPurchaseFactoryWrongPriceValueString() throws LineException {
        Purchase purchase = PurchaseFactory.getPurchaseFromFactory("water;ok;4");
    }

    @Test(expected = LineException.class)
    public void testPurchaseFactoryWrongDiscountValue() throws LineException {
        Purchase purchase = PurchaseFactory.getPurchaseFromFactory("water;70;4;0.5");
    }

    @Test(expected = LineException.class)
    public void testPurchaseFactoryWrongPriceValueFractional() throws LineException {
        Purchase purchase = PurchaseFactory.getPurchaseFromFactory("water;70.5;5;1");
    }

    @Test
    public void testPurchaseFactoryToString() throws LineException {
        Assert.assertEquals(new Purchase("mango", new Byn(111), 1).toString(),
                PurchaseFactory.getPurchaseFromFactory("mango;111;1").toString());
        Assert.assertEquals(new PriceDiscountPurchase("apple", new Byn(222), 2,
            new Byn(10)).toString(), PurchaseFactory.getPurchaseFromFactory("apple;222;2;10").toString());
    }

    @Test(expected = NonPositiveArgumentException.class)
    public void testBynWrongRubsNegative() throws NonPositiveArgumentException {
        new Byn(-25, 77);
    }

    @Test(expected = NonPositiveArgumentException.class)
    public void testBynWrongKopecksNegative() throws NonPositiveArgumentException {
        new Byn(10, -11);
    }

    @Test(expected = NonPositiveArgumentException.class)
    public void testBynWrongRubsKopecksNegative() throws NonPositiveArgumentException {
        new Byn(-10, -11);
    }

    @Test(expected = MoreThanMaxValueException.class)
    public void testBynWrongKopecksOutOfBounds() throws MoreThanMaxValueException {
        new Byn(15, 177);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPurchaseEmptyProductName() throws IllegalArgumentException {
        new Purchase(" ", new Byn(100), 10);
    }

    @Test(expected = NonPositiveArgumentException.class)
    public void testPurchaseWrongPriceNegative() throws NonPositiveArgumentException {
        new Purchase("meat", new Byn(-55), 10);
    }

    @Test(expected = NonPositiveArgumentException.class)
    public void testPurchaseWrongNumUnitsNegative() throws NonPositiveArgumentException {
        new Purchase("meat", new Byn(31), -10);
    }

    @Test(expected = NonPositiveArgumentException.class)
    public void testDiscountPurchaseWrongDiscountNegative() throws NonPositiveArgumentException {
        new PriceDiscountPurchase("apple", new Byn(12), 11, new Byn(-5));
    }

    @Test(expected = DiscountMoreOrEqualPriceException.class)
    public void testDiscountPurchaseDiscountMorePrice() throws DiscountMoreOrEqualPriceException {
        new PriceDiscountPurchase("apple", new Byn(12), 11, new Byn(13));
    }

    @Test
    public void testConstructorPurchaseList() {
        PurchaseList purchaseList = new PurchaseList(FILE_NAME, new PurchaseComparator());
        String stringPurchase = "bread;1.55;1;0.02;1.53\n" +
                "milk;1.31;2;2.62\n" +
                "bread;1.54;3;4.62\n" +
                "bread;1.45;5;7.25\n" +
                "potato;1.80;2;0.10;3.40\n" +
                "butter;3.70;1;3.70\n" +
                "butter;3.41;1;0.01;3.40\n" +
                "meat;11.00;2;0.80;20.40\n";
        Assert.assertEquals(stringPurchase, purchaseList.toString());
    }

    @Test
    public void testInsertIndexPurchase() {
        final int INDEX = 3;
        PurchaseList purchaseList = new PurchaseList();
        purchaseList.addArray(PURCHASE_ARRAY);
        Purchase testPurchase = new Purchase("water", new Byn(1111), 2);
        purchaseList.insert(INDEX, testPurchase);
        String strPurchaseList = "bread;1.45;5;7.25\n" +
                "bread;1.54;3;4.62\n" +
                "bread;1.55;1;0.02;1.53\n" +
                "water;11.11;2;22.22\n" +
                "butter;3.41;1;0.01;3.40\n" +
                "butter;3.70;1;3.70\n" +
                "meat;11.00;2;0.80;20.40\n" +
                "milk;1.31;2;2.62\n" +
                "potato;1.80;2;0.10;3.40\n";
        Assert.assertEquals(strPurchaseList, purchaseList.toString());
    }

    @Test
    public void testInsertWrongNegativeIndexPurchase() {
        final int INDEX = -1000;
        PurchaseList purchaseList = new PurchaseList();
        purchaseList.addArray(PURCHASE_ARRAY);
        Purchase testPurchase = new Purchase("water", new Byn(1111), 2);
        purchaseList.insert(INDEX, testPurchase);
        String strPurchaseList = "water;11.11;2;22.22\n" +
                "bread;1.45;5;7.25\n" +
                "bread;1.54;3;4.62\n" +
                "bread;1.55;1;0.02;1.53\n" +
                "butter;3.41;1;0.01;3.40\n" +
                "butter;3.70;1;3.70\n" +
                "meat;11.00;2;0.80;20.40\n" +
                "milk;1.31;2;2.62\n" +
                "potato;1.80;2;0.10;3.40\n";
        Assert.assertEquals(strPurchaseList, purchaseList.toString());
    }

    @Test
    public void testInsertWrongPositiveIndexPurchase() {
        final int INDEX = 1000;
        PurchaseList purchaseList = new PurchaseList();
        purchaseList.addArray(PURCHASE_ARRAY);
        Purchase testPurchase = new Purchase("water", new Byn(1111), 2);
        purchaseList.insert(INDEX, testPurchase);
        String strPurchaseList = "bread;1.45;5;7.25\n" +
                "bread;1.54;3;4.62\n" +
                "bread;1.55;1;0.02;1.53\n" +
                "butter;3.41;1;0.01;3.40\n" +
                "butter;3.70;1;3.70\n" +
                "meat;11.00;2;0.80;20.40\n" +
                "milk;1.31;2;2.62\n" +
                "potato;1.80;2;0.10;3.40\n" +
                "water;11.11;2;22.22\n";
        Assert.assertEquals(strPurchaseList, purchaseList.toString());
    }

    @Test
    public void testGetTotalCost() {
        PurchaseList purchaseList = new PurchaseList();
        purchaseList.addArray(PURCHASE_ARRAY);
        Assert.assertEquals(new Byn(4692), purchaseList.getTotalCost());
    }

    @Test
    public void testSearchPurchase() {
        PurchaseList purchaseList = new PurchaseList();
        purchaseList.addArray(PURCHASE_ARRAY);
        Assert.assertEquals(new Purchase("butter", new Byn(370), 1).toString(),
                purchaseList.getIndPurchases(purchaseList.binarySearch(new Purchase("butter", new Byn(370), 1))).toString());
    }

    @Test
    public void testSearchWrongPurchase() {
        PurchaseList purchaseList = new PurchaseList();
        purchaseList.addArray(PURCHASE_ARRAY);
        Assert.assertTrue(purchaseList.binarySearch(new Purchase("mango", new Byn(999999), 111)) < 0);
    }

    @Test
    public void testPurchaseSort() {
        PurchaseList purchaseList = new PurchaseList(FILE_NAME, new PurchaseComparator());
        PurchaseList expPurchaseList = new PurchaseList();
        expPurchaseList.addArray(PURCHASE_ARRAY);
        purchaseList.sort();
        Assert.assertEquals(expPurchaseList.toString(), purchaseList.toString());
    }

    @Test
    public void testRemoveValidIndex() {
        final int IND = 4;
        final int START_IND = 3;
        final int END_IND = 7;
        PurchaseList purchaseList = new PurchaseList();
        purchaseList.addArray(PURCHASE_ARRAY);
        Purchase[] expPurchaseArr = {
                new Purchase("bread", new Byn(145), 5),
                new Purchase("bread", new Byn(154), 3),
                new PriceDiscountPurchase("bread", new Byn(155), 1, new Byn(2)),
                new PriceDiscountPurchase("potato", new Byn(180), 2, new Byn(10))
        };
        PurchaseList expPurchaseList = new PurchaseList();
        expPurchaseList.addArray(expPurchaseArr);
        Assert.assertEquals(IND, purchaseList.remove(START_IND, END_IND));
        Assert.assertEquals(expPurchaseList.toString(), purchaseList.toString());
    }

    @Test
    public void testRemoveEqualStartEndIndex() {
        final int IND = 0;
        final int START_IND = 3;
        final int END_IND = 3;
        PurchaseList purchaseList = new PurchaseList();
        purchaseList.addArray(PURCHASE_ARRAY);
        Purchase[] expPurchaseArr = {
                new Purchase("bread", new Byn(145), 5),
                new Purchase("bread", new Byn(154), 3),
                new PriceDiscountPurchase("bread", new Byn(155), 1, new Byn(2)),
                new PriceDiscountPurchase("butter", new Byn(341), 1, new Byn(1)),
                new Purchase("butter", new Byn(370), 1),
                new PriceDiscountPurchase("meat", new Byn(1100), 2, new Byn(80)),
                new Purchase("milk", new Byn(131), 2),
                new PriceDiscountPurchase("potato", new Byn(180), 2, new Byn(10))
        };
        PurchaseList expPurchaseList = new PurchaseList();
        expPurchaseList.addArray(expPurchaseArr);
        Assert.assertEquals(IND, purchaseList.remove(START_IND, END_IND));
        Assert.assertEquals(expPurchaseList.toString(), purchaseList.toString());
    }

    @Test
    public void testRemoveWrongEndPositiveIndex() {
        final int IND = 4;
        final int START_IND = 4;
        final int END_IND = 20;
        PurchaseList purchaseList = new PurchaseList();
        purchaseList.addArray(PURCHASE_ARRAY);
        Purchase[] expPurchaseArr = {
                new Purchase("bread", new Byn(145), 5),
                new Purchase("bread", new Byn(154), 3),
                new PriceDiscountPurchase("bread", new Byn(155), 1, new Byn(2)),
                new PriceDiscountPurchase("butter", new Byn(341), 1, new Byn(1))
        };
        PurchaseList expPurchaseList = new PurchaseList();
        expPurchaseList.addArray(expPurchaseArr);
        Assert.assertEquals(IND, purchaseList.remove(START_IND, END_IND));
        Assert.assertEquals(expPurchaseList.toString(), purchaseList.toString());
    }

    @Test
    public void testRemoveWrongStartNegativeIndex() {
        final int IND = 2;
        final int START_IND = -3;
        final int END_IND = 2;
        PurchaseList purchaseList = new PurchaseList();
        purchaseList.addArray(PURCHASE_ARRAY);
        Purchase[] expPurchaseArr = {
                new PriceDiscountPurchase("bread", new Byn(155), 1, new Byn(2)),
                new PriceDiscountPurchase("butter", new Byn(341), 1, new Byn(1)),
                new Purchase("butter", new Byn(370), 1),
                new PriceDiscountPurchase("meat", new Byn(1100), 2, new Byn(80)),
                new Purchase("milk", new Byn(131), 2),
                new PriceDiscountPurchase("potato", new Byn(180), 2, new Byn(10))
        };
        PurchaseList expPurchaseList = new PurchaseList();
        expPurchaseList.addArray(expPurchaseArr);
        Assert.assertEquals(IND, purchaseList.remove(START_IND, END_IND));
        Assert.assertEquals(expPurchaseList.toString(), purchaseList.toString());
    }

    @Test
    public void testRemoveWrongStartIndex() {
        final int IND = 0;
        final int START_IND = 5;
        final int END_IND = 2;
        PurchaseList purchaseList = new PurchaseList();
        purchaseList.addArray(PURCHASE_ARRAY);
        Purchase[] expPurchaseArr = {
                new Purchase("bread", new Byn(145), 5),
                new Purchase("bread", new Byn(154), 3),
                new PriceDiscountPurchase("bread", new Byn(155), 1, new Byn(2)),
                new PriceDiscountPurchase("butter", new Byn(341), 1, new Byn(1)),
                new Purchase("butter", new Byn(370), 1),
                new PriceDiscountPurchase("meat", new Byn(1100), 2, new Byn(80)),
                new Purchase("milk", new Byn(131), 2),
                new PriceDiscountPurchase("potato", new Byn(180), 2, new Byn(10))
        };
        PurchaseList expPurchaseList = new PurchaseList();
        expPurchaseList.addArray(expPurchaseArr);
        Assert.assertEquals(IND, purchaseList.remove(START_IND, END_IND));
        Assert.assertEquals(expPurchaseList.toString(), purchaseList.toString());
    }

    @Test
    public void testRemoveWrongStartEndNegativeIndex() {
        final int IND = 0;
        final int START_IND = -2;
        final int END_IND = -5;
        PurchaseList purchaseList = new PurchaseList();
        purchaseList.addArray(PURCHASE_ARRAY);
        Purchase[] expPurchaseArr = {
                new Purchase("bread", new Byn(145), 5),
                new Purchase("bread", new Byn(154), 3),
                new PriceDiscountPurchase("bread", new Byn(155), 1, new Byn(2)),
                new PriceDiscountPurchase("butter", new Byn(341), 1, new Byn(1)),
                new Purchase("butter", new Byn(370), 1),
                new PriceDiscountPurchase("meat", new Byn(1100), 2, new Byn(80)),
                new Purchase("milk", new Byn(131), 2),
                new PriceDiscountPurchase("potato", new Byn(180), 2, new Byn(10))
        };
        PurchaseList expPurchaseList = new PurchaseList();
        expPurchaseList.addArray(expPurchaseArr);
        Assert.assertEquals(IND, purchaseList.remove(START_IND, END_IND));
        Assert.assertEquals(expPurchaseList.toString(), purchaseList.toString());
    }
}
