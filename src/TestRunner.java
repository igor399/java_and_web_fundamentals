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
        Byn byn1 = new Byn(300);
        Byn byn2 = new Byn(140);
        Assert.assertEquals("4.40", byn1.add(140).toString());
    }

    @Test
    public void testSubtract() {
        Byn byn1 = new Byn(300);
        Byn byn2 = new Byn(200);
        Assert.assertEquals("1.00", byn1.subtract(200).toString());
    }

    @Test
    public void testBynMultiply() {
        Byn byn1 = new Byn(400);
        byn1.multiply(2);
        Assert.assertEquals("8.00", byn1.toString());
    }

    @Test
    public void testBynToString() {
        Assert.assertEquals("90.00", new Byn(9000).toString());
        Assert.assertEquals("0.00", new Byn(0).toString());
    }

    @Test
    public void testBynCompareTo() {
        Byn byn1 = new Byn(800);
        Byn byn2 = new Byn(300);
        Byn byn3 = new Byn(800);
        Assert.assertEquals(500, byn1.compareTo(byn2));
        Assert.assertEquals(-500, byn2.compareTo(byn1));
        Assert.assertEquals(0, byn1.compareTo(byn3));
    }

    @Test
    public void testBynEquals() {
        Byn byn1 = new Byn(100);
        Byn byn2 = new Byn(140);
        Byn byn3 = new Byn(100);
        Assert.assertTrue(byn1.equals(byn3));
        Assert.assertFalse(byn1.equals(byn2));
    }

    @Test
    public void testEquals() {

        Purchase purchase1 = new Purchase("Apple", new Byn(30), 15);
        Purchase purchase2 = new Purchase("Apple", new Byn(30), 15);
        Purchase purchase3 = new Purchase("Milk", new Byn(12), 8);
        Purchase purchase4 = new Purchase("Milk", new Byn(11), 5);
        Assert.assertTrue(purchase1.equals(purchase2));
        Assert.assertFalse(purchase3.equals(purchase4));
        Assert.assertFalse(purchase2.equals(purchase3));
    }

    @Test
    public void testGetCost() {
        Assert.assertEquals(new Byn(900), new Purchase("Apple", new Byn(20), 45).getCost());
        Assert.assertEquals(new Byn(810), new RatioDiscountPurchase("Apple", new Byn(20), 45, 10).getCost());
        Assert.assertEquals(new Byn(450), new ValueDiscountPurchase("Apple", new Byn(20), 45, 10).getCost());
    }

    @Test
    public void testCreatePurchase() {
        Purchase purchase1 = new Purchase("Apple", new Byn(140), 12);
        Assert.assertEquals("Apple", purchase1.getProductName());
        Assert.assertEquals(new Byn(140), purchase1.getPrice());
        Assert.assertEquals(12, purchase1.getNumUnits());

    }
}