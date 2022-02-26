import org.junit.Assert;
import org.junit.Test;
import src.by.epam.lab.Purchase;

public class TestRunner {
    @Test
    public void testGetCost() {
        Purchase p1 = new Purchase(1,10.3 ,0);
        Assert.assertEquals(200,p1.getCost());
        Purchase p2 = new Purchase(10,15.8 ,1);
        Assert.assertEquals(2100,p2.getCost());
        Purchase p3 = new Purchase(5,43.0 ,2);
        Assert.assertEquals(700,p3.getCost());
        Purchase p4 = new Purchase(75,71.2 ,3);
        Assert.assertEquals(5400,p4.getCost());
        Purchase p5 = new Purchase(95,63.0 ,4);
        Assert.assertEquals(8800,p5.getCost());
        Purchase p6 = new Purchase(16,37.7 ,5);
        Assert.assertEquals(2500,p6.getCost());
        Purchase p7 = new Purchase(35,13.9 ,6);
        Assert.assertEquals(7500,p7.getCost());
    }

    @Test
    public void testCurrencyConvention(){
        Assert.assertEquals("2.50", Purchase.currencyConvention(250));
        Assert.assertEquals("30.00", Purchase.currencyConvention(3000));
        Assert.assertEquals("0.09", Purchase.currencyConvention(9));
    }
}
