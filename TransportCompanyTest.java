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
    
    Passenger p1;
    Passenger p2;
    
    Taxi taxi1;
    Taxi taxi2;
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
        Location pickup1, pickup2, destination1, destination2;
        int arrivalTime, creditCard;
        Reliability reliable;
        
        pickup1 = new Location(0,0);
        pickup2 = new Location(6,4);
        destination1 = new Location(3,2);
        destination2 = new Location(4,1);
        
        Passenger p1 = new PassengerVip(pickup1, destination1, "Javier", 1, 35000, Reliability.HIGH);
        Passenger p2 = new PassengerNoVip(pickup2, destination2, "Juan", 1, 13000, Reliability.LOW);
        
        Taxi taxi1 = new TaxiShuttle(company, new Location(0, 0),"T1", FuelComsumption.MEDIUM, 4);
        Taxi taxi2 = new TaxiExclusive(company2, new Location(0, 0),"T2",
                    FuelComsumption.MEDIUM, 7000);
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
        assertEquals(false, company.requestPickup(p2));
        assertEquals(false, company.requestPickup(p1));
        assertEquals(true, company.requestPickup(p1));
    }
    
    /**
     * Test of the arrivedAtPickup() method
     */    
    @Test
    public void testArrivedAtPickup()
    {        
        assertEquals(false, company.requestPickup(p2));
        assertEquals(false, company.requestPickup(p1));
        assertEquals(true, company.requestPickup(p1));
    }
    
    /**
     * Test of the arrivedAtDestination() method
     */    
    @Test
    public void testArrivedAtDestination()
    {
        taxi1.pickup(p1);
        for(int i = 1; i <= taxi1.distanceToTheTargetLocation(); i++){
            taxi1.act();
        }
        assertEquals(p1.getPickup(), taxi1.getTargetLocation());
        
        assertEquals(false, company.requestPickup(p2));
        assertEquals(false, company.requestPickup(p1));
        assertEquals(true, company.requestPickup(p1));
    }
}
