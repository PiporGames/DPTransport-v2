import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class TransportCompanyTest.
 *
 * @author  José, Manuel y David
 * @version 2023
 */
public class TransportCompanyTest
{
    TransportCompany company;
    TransportCompany company2;
    /**
     * Default constructor for test class TransportCompanyTest
     */
    public TransportCompanyTest(){}

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        company = new TransportCompany("Compañía Taxis Cáceres");
        company2 = new TransportCompany("ALSA Taxis");        
    }
    
    /**
     * Test creation and the initial state of Transport Company
     */
    @Test
    public void testCreation()
    {
        assertEquals("Compañía Taxis Cáceres", company.getName());
        assertEquals("ALSA Taxis", company.getName());
    }
    
    /**
     * Test of the requestPickup() method
     */    
    @Test
    public void testRequestPickup()
    {
        Location pickup, destination;
        String name;
        int arrivalTime, creditCard;
        Reliability reliable;
        
        pickup
        Passenger p1 = new PassengerVip(pickup, destination, name, arrivalTime, creditCard,reliable);
    }
    
}
