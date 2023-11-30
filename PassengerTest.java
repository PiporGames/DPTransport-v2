import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class PassengerTest.
 *
 * @author  Jose, Manuel, & David
 * @version 2023.10.10
 */
public class PassengerTest
{           
    Passenger p1;
    Passenger p2;    
    /**
     * Default constructor for test class PassengerTest
     */
    public PassengerTest() {}

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        p1 = new PassengerNoVip(new Location(6, 6), new Location(5,2),"Lucy", 2, 500, reliability.HIGH);
        p2 = new PassengerVip(new Location(4, 4), new Location(2,1),"Stuart", 2, 5500, reliability.LOW);
    }

    /**
     * Test basic creation of a passenger.
     * Ensure that the pickup and destination locations
     * have been set.
     */
    @Test
    public void testCreation()
    {
        assertEquals(p1.getPickup(),new Location(6, 6));
        assertEquals(p1.getDestination(),new Location(5, 2));
        assertEquals(p1.getName(),"Lucy");
        assertEquals(p1.getTaxiName(),null);
        assertEquals(p1.getArrivalTime(),2);
        assertEquals(p1.getCreditCard(),500);
        assertEquals(p1.getReliability(),reliability.HIGH);
        
        assertEquals(p2.getPickup(),new Location(4, 4));
        assertEquals(p2.getDestination(),new Location(2, 1));
        assertEquals(p2.getName(),"Stuart");
        assertEquals(p2.getTaxiName(),null);
        assertEquals(p2.getArrivalTime(),2);
        assertEquals(p2.getCreditCard(),5500);
        assertEquals(p2.getReliability(),reliability.LOW);
    }

    /**
     * Test of the pay() method.
     * Ensure that this method decrements the credit card value.
     */
    @Test
    public void testDecrementCreditCard()
    {
        p1.pay();
        assertEquals(p1.getCreditCard(), 470);
        
        p2.pay();
        assertEquals(p2.getCreditCard(), 4890);
    }
    
    /**
     * Test of the ac() method.
     * Ensure that this method decrements the credit card value and returns the correct evaluation score.
     */
    @Test
    public void testAct()
    {
        int score1 = p1.act();
        assertEquals(p1.getCreditCard(), 470);
        assertEquals(score1, 20);
        
        int score2 = p2.act();
        assertEquals(p2.getCreditCard(), 4890);
        assertEquals(score2, 25);
    }
    
        /**
     * Test various other set() methods.
     * Ensure that the value set by these methods are stored correctly.
     */
    @Test
    public void testSetters()
    {
        p1.setCreditCard(700);
        assertEquals(p1.getCreditCard(), 700);
        p1.setTaxiName("T1");
        assertEquals(p1.getTaxiName(), "T1");
        
        p2.setCreditCard(200);
        assertEquals(p2.getCreditCard(), 200);
        p2.setTaxiName("T2");
        assertEquals(p2.getTaxiName(), "T2");
    }
}
