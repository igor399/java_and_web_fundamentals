import by.epam.lab.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

public class TestRunner {
    @Test
    public void testAdd() {
        Assert.assertEquals(new Byn(440), new Byn(300).add(new Byn(140)));
    }

    @Test
    public void testSubtract() {
        Assert.assertEquals(new Byn(100), new Byn(300).subtract(new Byn(200)));
    }

    @Test
    public void testBynMultiply() {
        Assert.assertEquals(new Byn(800), new Byn(400).multiply(2));
    }

    @Test
    public void testBynMultiplyDouble() {
        Assert.assertEquals(new Byn(1410), new Byn(97).multiply(14.54,RoundMethod.ROUND,0));
        Assert.assertEquals(new Byn(1400), new Byn(97).multiply(14.54,RoundMethod.ROUND,2));
        Assert.assertEquals(new Byn(1400), new Byn(100).multiply(14.54,RoundMethod.FLOOR,2));
        Assert.assertEquals(new Byn(1500), new Byn(100).multiply(14.54,RoundMethod.CEIL,2));
    }

    @Test
    public void testBynRound() {
        Assert.assertEquals(new Byn(100), new Byn(95).round(RoundMethod.ROUND,2));
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

    @Test
    public void testBynCompareTo() {
        Assert.assertEquals(500, new Byn(800).compareTo(new Byn(300)));
        Assert.assertEquals(-500, new Byn(300).compareTo(new Byn(800)));
        Assert.assertEquals(0, new Byn(800).compareTo(new Byn(800)));
    }

    @Test
    public void testBynEquals() {
        Assert.assertTrue(new Byn(133).equals(new Byn(133)));
        Assert.assertFalse(new Byn(55).equals(new Byn(99)));
    }

    @Test
    public void testEquals() {
        Assert.assertTrue(new Purchase("Apple", new Byn(30), 15).equals(new Purchase("Apple", new Byn(30), 15)));
        Assert.assertFalse(new Purchase("Milk", new Byn(12), 8).equals(new Purchase("Milk", new Byn(11), 5)));
        Assert.assertFalse(new Purchase("Apple", new Byn(30), 15).equals(new Purchase("Milk", new Byn(12), 8)));
    }

    @Test
    public void testGetCost() {
        Assert.assertEquals(new Byn(900), new Purchase("Apple", new Byn(20), 45).getCost());
        Assert.assertEquals(new Byn(810), new PercentDiscountPurchase("Apple", new Byn(20), 45, 10).getCost());
    }

    @Test
    public void testCreatePurchasesFactory() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader("src/in.txt"));
        scanner.useLocale(Locale.ENGLISH);
        Purchase[] purchases = new Purchase[6];
        for (int i = 0; i < purchases.length; i++) {
            purchases[i] = PurchaseFactory.getPurchaseFromFactory(scanner);
        }
        Assert.assertEquals("Purchase", purchases[0].getClass().getSimpleName());
        Assert.assertEquals("PriceDiscountPurchase", purchases[1].getClass().getSimpleName());
        Assert.assertEquals("PercentDiscountPurchase", purchases[2].getClass().getSimpleName());
        Assert.assertEquals("PercentDiscountPurchase", purchases[3].getClass().getSimpleName());
        Assert.assertEquals("Purchase", purchases[4].getClass().getSimpleName());
        Assert.assertEquals("Purchase", purchases[5].getClass().getSimpleName());
    }
}
