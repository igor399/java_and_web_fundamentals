import by.epam.lab.*;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class TestRunner {
    @Test
    public void add() {
        Assert.assertEquals(new Byn(500), new Byn(300).add(new Byn(200)));
    }

    @Test
    public void subtract() {
        Assert.assertEquals(new Byn(100), new Byn(300).subtract(new Byn(200)));
    }

    @Test
    public void multiply() {
        Assert.assertEquals(new Byn(800), new Byn(400).multiply(2));
    }

    @Test
    public void testMultiply() {
        Assert.assertEquals(new Byn(1410), new Byn(97).multiply(14.54, RoundMethod.ROUND, 0));
        Assert.assertEquals(new Byn(1400), new Byn(97).multiply(14.54, RoundMethod.ROUND, 2));
        Assert.assertEquals(new Byn(1400), new Byn(100).multiply(14.54, RoundMethod.FLOOR, 2));
        Assert.assertEquals(new Byn(1500), new Byn(100).multiply(14.54, RoundMethod.CEIL, 2));
    }

    @Test
    public void round() {
        Assert.assertEquals(new Byn(100), new Byn(95).round(RoundMethod.ROUND, 2));
    }

    @Test
    public void testToString() {
        Assert.assertEquals("3.50", new Byn(350).toString());
        Assert.assertEquals("3.05", new Byn(305).toString());
        Assert.assertEquals("0.00", new Byn(0).toString());
        Assert.assertEquals("3.00", new Byn(300).toString());
        Assert.assertEquals("0.05", new Byn(5).toString());
        Assert.assertEquals("105.00", new Byn(10500).toString());
    }

    @Test
    public void testEquals() {
        Assert.assertTrue(new Byn(133).equals(new Byn(133)));
        Assert.assertFalse(new Byn(55).equals(new Byn(99)));
    }

    @Test
    public void compareTo() {
        Assert.assertEquals(500, new Byn(800).compareTo(new Byn(300)));
        Assert.assertEquals(-500, new Byn(300).compareTo(new Byn(800)));
        Assert.assertEquals(0, new Byn(800).compareTo(new Byn(800)));
    }

    @Test
    public void getCost() {
        Assert.assertEquals(new Byn(300), new PercentDiscountPurchase(new Product("Mango", new Byn(20)), 20, 11.7).getCost());
        Assert.assertEquals(new Byn(400), new PercentDiscountPurchase(new Product("Mango", new Byn(20)), 20, 0).getCost());
        Assert.assertEquals(new Byn(400), new PriceDiscountPurchase(new Product("Apple", new Byn(55)), 10, new Byn(10)).getCost());
        Assert.assertEquals(new Byn(500), new PriceDiscountPurchase(new Product("Apple", new Byn(55)), 10, new Byn(0)).getCost());
        Assert.assertEquals(new Byn(800), new TransportExpensesPurchase(new Product("Pen", new Byn(45)), 19, new Byn(20)).getCost());
        Assert.assertEquals(new Byn(700), new TransportExpensesPurchase(new Product("Pen", new Byn(40)), 19, new Byn(0)).getCost());
    }

    @Test
    public void testBynToString() {
        Assert.assertEquals("3.50", new Byn(350).toString());
        Assert.assertEquals("3.05", new Byn(305).toString());
        Assert.assertEquals("0.00", new Byn(0).toString());
        Assert.assertEquals("3.00", new Byn(300).toString());
        Assert.assertEquals("0.05", new Byn(5).toString());
        Assert.assertEquals("105.00", new Byn(10500).toString());
    }

    private static int search(AbstractPurchase[] purchases, Byn byn) {
        return Arrays.binarySearch(purchases, new PercentDiscountPurchase(new Product("", byn), 0, 0));
    }

    @Test
    public void testBinarySearch() {
        final Product PRODUCT = new Product("Mango", new Byn(500));
        AbstractPurchase[] purchases = {
                new PriceDiscountPurchase(PRODUCT, 1, new Byn(0)),
                new PriceDiscountPurchase(PRODUCT, 3, new Byn(50)),
                new PercentDiscountPurchase(PRODUCT, 20, 9.3),
                new PercentDiscountPurchase(PRODUCT, 6, 5.5),
                new TransportExpensesPurchase(PRODUCT, 2, new Byn(13)),
                new TransportExpensesPurchase(PRODUCT, 5, new Byn(325)),

        };
        Arrays.sort(purchases);
        Assert.assertEquals(0, search(purchases, new Byn(500)));
        Assert.assertEquals(3, search(purchases, new Byn(2835)));
        Assert.assertEquals(5, search(purchases, new Byn(2825)));
        int index = search(purchases, new Byn(10));
        Assert.assertTrue(index < 0);
        index = search(purchases, new Byn(22));
        Assert.assertTrue(index < 0);
    }
}
