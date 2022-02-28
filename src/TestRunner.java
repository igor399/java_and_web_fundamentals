import by.epam.lab.*;
import org.junit.Assert;
import org.junit.Test;

public class TestRunner {

    @Test
    public void testPurchaseInit() {
        Purchase p1 = new Purchase(0, 0.0, 0);
        Assert.assertEquals(0, p1.getCost());
        Purchase p2 = new Purchase(0, 0.0, -1);
        Assert.assertEquals(0, p2.getCost());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsException() {
        Purchase p1 = new Purchase(0, 0.0, -1);
        Assert.assertEquals(0, p1.getCost());
        Purchase p2 = new Purchase(0, 0.0, 8);
        Assert.assertEquals(0, p2.getCost());
    }

    @Test
    public void testGetCost() {
        Purchase p1 = new Purchase(1, 10, 0);
        Assert.assertEquals(200, p1.getCost());
        Purchase p2 = new Purchase(10, 15.9, 1);
        Assert.assertEquals(2100, p2.getCost());
        Purchase p3 = new Purchase(5, 43.5, 2);
        Assert.assertEquals(700, p3.getCost());
        Purchase p4 = new Purchase(75, 71.8, 3);
        Assert.assertEquals(5300, p4.getCost());
    }

    @Test
    public void testCurrencyConvention() {
        Assert.assertEquals("3.50", Util.currencyConvention(350));
        Assert.assertEquals("3.05", Util.currencyConvention(305));
        Assert.assertEquals("3.00", Util.currencyConvention(300));
        Assert.assertEquals("0.05", Util.currencyConvention(5));
        Assert.assertEquals("0.00", Util.currencyConvention(0));
        Assert.assertEquals("1005.00", Util.currencyConvention(100500));
    }
}

