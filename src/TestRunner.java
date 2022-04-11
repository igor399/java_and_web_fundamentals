import by.epam.lab.beans.*;
import by.epam.lab.services.PurchaseComparator;
import by.epam.lab.exceptions.LineException;
import by.epam.lab.services.PurchaseFactory;
import by.epam.lab.services.PurchaseList;
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
    public void testInsertIndex() {
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
    public void testInsertWrongNegativeIndex() {
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
    public void testInsertWrongPositiveIndex() {
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
        purchaseList.remove(START_IND, END_IND);
        Assert.assertEquals(expPurchaseList.toString(), purchaseList.toString());
    }

    @Test
    public void testRemoveWrongEndPositiveIndex() {
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
        purchaseList.remove(START_IND, END_IND);
        Assert.assertEquals(expPurchaseList.toString(), purchaseList.toString());
    }

    @Test
    public void testRemoveWrongStartNegativeIndex() {
        final int START_IND = -5;
        final int END_IND = 2;
        PurchaseList purchaseList = new PurchaseList();
        purchaseList.addArray(PURCHASE_ARRAY);
        Purchase[] expectedPurchaseArray = {
                new PriceDiscountPurchase("bread", new Byn(155), 1, new Byn(2)),
                new PriceDiscountPurchase("butter", new Byn(341), 1, new Byn(1)),
                new Purchase("butter", new Byn(370), 1),
                new PriceDiscountPurchase("meat", new Byn(1100), 2, new Byn(80)),
                new Purchase("milk", new Byn(131), 2),
                new PriceDiscountPurchase("potato", new Byn(180), 2, new Byn(10))
        };
        PurchaseList expPurchaseList = new PurchaseList();
        expPurchaseList.addArray(expectedPurchaseArray);
        purchaseList.remove(START_IND, END_IND);
        Assert.assertEquals(expPurchaseList.toString(), purchaseList.toString());
    }

    @Test
    public void testRemoveWrongStartIndex() {
        final int START_IND = 5;
        final int END_IND = 2;
        PurchaseList purchaseList = new PurchaseList();
        purchaseList.addArray(PURCHASE_ARRAY);
        Purchase[] expectedPurchaseArray = {
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
        expPurchaseList.addArray(expectedPurchaseArray);
        purchaseList.remove(START_IND, END_IND);
        Assert.assertEquals(expPurchaseList.toString(), purchaseList.toString());
    }

    @Test
    public void testPurchaseFactory() throws LineException {
        Assert.assertEquals(new Purchase("mango", new Byn(111), 1).toString(),
                PurchaseFactory.getPurchaseFromFactory("mango;111;1").toString());
        Assert.assertEquals(new PriceDiscountPurchase("apple", new Byn(222), 2,
                new Byn(10)).toString(), PurchaseFactory.getPurchaseFromFactory("apple;222;2;10").toString());
    }

    @Test(expected = LineException.class)
    public void testPurchaseFactoryALlInvalidValues() throws LineException {
        Purchase purchase = PurchaseFactory.getPurchaseFromFactory(";;");
    }

    @Test(expected = LineException.class)
    public void testPurchaseFactoryFirstInvalidValues() throws LineException {
        Purchase purchase = PurchaseFactory.getPurchaseFromFactory(";145;5");
    }

    @Test(expected = LineException.class)
    public void testPurchaseFactoryFirstSecondInvalidValues() throws LineException {
        Purchase purchase = PurchaseFactory.getPurchaseFromFactory("potato;0;0");
    }

    @Test(expected = LineException.class)
    public void testPurchaseFactoryWrongFieldNum() throws LineException {
        Purchase purchase = PurchaseFactory.getPurchaseFromFactory("potato");
    }

    @Test(expected = LineException.class)
    public void testPurchaseFactoryOutOfBoundsInvalidValues() throws LineException {
        Purchase purchase = PurchaseFactory.getPurchaseFromFactory("water;15;4;0.1;cold");
    }
}
