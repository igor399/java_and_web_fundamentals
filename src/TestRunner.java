import by.epam.lab.*;
import org.junit.Assert;
import org.junit.Test;

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
        Assert.assertTrue(new Byn(100).equals(new Byn(100)));
        Assert.assertFalse(new Byn(100).equals(new Byn(140)));
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
        Assert.assertEquals(new Byn(810), new RatioDiscountPurchase("Apple", new Byn(20), 45, 10).getCost());
        Assert.assertEquals(new Byn(450), new ValueDiscountPurchase("Apple", new Byn(20), 45, 10).getCost());
    }

    @Test
    public void testCreatePurchase() {
        Purchase purchase1 = new Purchase("Apple", new Byn(253), 42);
        Assert.assertEquals("Apple", purchase1.getProductName());
        Assert.assertEquals(new Byn(253), purchase1.getPrice());
        Assert.assertEquals(42, purchase1.getNumUnits());
        ValueDiscountPurchase purchase2 = new ValueDiscountPurchase("Meat", new Byn(250), 5,3);
        Assert.assertEquals("Meat", purchase2.getProductName());
        Assert.assertEquals(new Byn(250), purchase2.getPrice());
        Assert.assertEquals(5, purchase2.getNumUnits());
        Assert.assertEquals(null, purchase2.getDiscount());
        RatioDiscountPurchase purchase3 = new RatioDiscountPurchase("Mango", new Byn(250), 10, 10);
        Assert.assertEquals("Mango", purchase3.getProductName());
        Assert.assertEquals(new Byn(250), purchase3.getPrice());
        Assert.assertEquals(10, purchase3.getNumUnits());
        Assert.assertEquals(10, purchase3.getDiscount(), 10);
    }
}
