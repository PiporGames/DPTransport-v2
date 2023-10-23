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
        p1 = new Passenger(new Location(6, 6),
                new Location(5,2),"Lucy", "T1");
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
        assertEquals(p1.getTaxiName(),"T1");
    }

    /**
     * Test of the getTaxiName method.
     * Ensure that this method gets and returns the name of the taxi correctly.
     */
    @Test
    public void testGetTaxiName()
    {
        assertEquals(p1.getTaxiName(), "T1");
    }

    /**
     * Test of the getPickupLocation method.
     * Ensure that this method gets and returns the pickup location correctly.
     */
    @Test
    public void testGetPickupLocation ()
    {
        assertEquals(p1.getPickup(), new Location(6,6));
    }
}
