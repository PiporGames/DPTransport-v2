import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class TaxiTest.
 *
 * @author  Jose, Manuel & David
 * @version 2023.10.25 DP classes 
 */
public class TaxiTest
{
    private Taxi taxi;
    private Taxi taxi2;
    private Passenger passenger;
    private Passenger passenger2;
    
    /**
     * Default constructor for test class TaxiTest
     */
    public TaxiTest()
    {
    }

    /**
     * Create a taxi.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        TransportCompany company = new TransportCompany("Compañía Taxis Cáceres");
        TransportCompany company2 = new TransportCompany("ALSA Taxis");
        // Starting position for the taxi.
        
        Location taxiLocation = new Location(0, 0);
        
        Location pickup = new Location(1, 2);
        Location destination = new Location(5, 6);

        passenger = new PassengerNoVip(pickup, destination,"Kevin");
        taxi = new TaxiShuttle(company, taxiLocation,"T1", 
                    FuelComsumption.MEDIUM, 4);
     
        Location taxiLocation2 = new Location(0, 0);
        
        Location pickup2 = new Location(0, 0);
        Location destination2 = new Location(4, 4);

        passenger2 = new PassengerVip(pickup2, destination2,"Clara");
        taxi2 = new TaxiExclusive(company2, taxiLocation2,"T2",
                    FuelComsumption.MEDIUM, 7000);
        
    }

    /**
     * Test creation and the initial state of a taxi.
     */
    @Test
    public void testCreation()
    {
        assertEquals(true, taxi.isFree());
        assertEquals("T1", taxi.getName());
        assertEquals(new Location(0,0), taxi.getLocation());
        assertEquals(new Location(5,6),taxi.getTargetLocation());
        assertEquals(0,taxi.getIdleCount());
        }

    /**
     * Test the setters and getters of the Taxi class.
     */
    @Test
    public void testSettersGetters(){
        taxi.setLocation(new Location(3,3));
        assertEquals(new Location(3,3), taxi.getLocation());
        taxi.setTargetLocation(new Location(4,4));
        assertEquals(new Location(4,4), taxi.getTargetLocation());
        taxi.setPickupLocation(new Location(5,5));
        assertEquals(new Location(5,5), taxi.getTargetLocation());
        taxi.clearTargetLocation();
        assertEquals(null, taxi.getTargetLocation());
    }
        
    /**
     * Test that a taxi is no longer free after it has
     * picked up a passenger.
     */
    @Test
    public void testPickup()
    {
        taxi.pickup(passenger);
        assertEquals(false, taxi.isFree());
        assertEquals(passenger.getDestination(), taxi.getTargetLocation());
 
        taxi2.pickup(passenger2);
        assertEquals(false, taxi2.isFree());
        assertEquals(passenger2.getDestination(), taxi2.getTargetLocation());
    }

    /**
     * Test that a taxi becomes free again after offloading
     * a passenger.
     */
    public void testOffload()
    {
        taxi.offloadPassenger();
        assertEquals(true, taxi.isFree());
        assertEquals(null, taxi.getTargetLocation());
        
        taxi2.offloadPassenger();
        assertEquals(true, taxi2.isFree());
        assertEquals(null, taxi2.getTargetLocation());
    }

    /**
     * Test that a taxi picks up and delivers a passenger within
     * a reasonable number of steps.
     */
    public void testDelivery()
    {
        taxi.pickup(passenger);
        for(int i = 1; i <= taxi.distanceToTheTargetLocation(); i++){
            taxi.act();
        }
        assertEquals(null, taxi.getTargetLocation());
        
        taxi2.pickup(passenger2);
        for(int i = 1; i <= taxi2.distanceToTheTargetLocation(); i++){
            taxi2.act();
        }
        assertEquals(null, taxi2.getTargetLocation());
    }
}

