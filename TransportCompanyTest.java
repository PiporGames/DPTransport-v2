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
    Passenger p3;
    
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
        
        pickup1 = new Location(0,0);
        pickup2 = new Location(6,4);
        destination1 = new Location(3,2);
        destination2 = new Location(4,1);
        
        p1 = new PassengerVip(pickup1, destination1, "Javier", 1, 35000, Reliability.HIGH);
        p2 = new PassengerNoVip(pickup2, destination2, "Juan", 2, 13000, Reliability.LOW);
        p3 = new PassengerNoVip(pickup2, destination2, "Pepe", 3, 13000, Reliability.LOW); 
        
        taxi1 = new TaxiExclusive(company2, new Location(0, 0),"T1",FuelComsumption.MEDIUM, 7000);
        taxi2 = new TaxiShuttle(company, new Location(0, 0),"T2", FuelComsumption.MEDIUM, 4);
    }
    
    /**
     * Test creation and the initial state of Transport Company
     */
    @Test
    public void testCreation()
    {
        assertEquals("Compañía Taxis Cáceres", company.getName());
        assertEquals("ALSA Taxis", company2.getName());
    }
    
    /**
     * Test of the requestPickup() method
     */    
    @Test
    public void testRequestPickup()
    {
        assertEquals(false, company.requestPickup(p1));
        company.addPassenger(p1);
        company.addVehicle(taxi1);
        assertEquals(true, company.requestPickup(p1));
        
        assertEquals(false, company2.requestPickup(p2));
        company2.addPassenger(p2);
        company2.addVehicle(taxi2);
        assertEquals(true, company.requestPickup(p2));
    }
    
    /**
     * Test of the arrivedAtPickup() method
     */    
    @Test
    public void testArrivedAtPickup()
    {   
        company.addPassenger(p1);
        company.addVehicle(taxi1);
        company.requestPickup(p1);
        taxi1.setLocation(p1.getPickup());
        company.arrivedAtPickup(taxi1);
        assertEquals(p1.getDestination(), taxi1.getTargetLocation());

        company2.addPassenger(p2);
        company2.addVehicle(taxi2);
        company2.requestPickup(p2);
        taxi2.setLocation(p2.getPickup());
        company2.arrivedAtPickup(taxi2);
        assertEquals(p2.getDestination(), taxi2.getTargetLocation());
    }
    
    /**
     * Test of the arrivedAtDestination() method
     */    
    @Test
    public void testArrivedAtDestination()
    {
        company.addPassenger(p1);
        company.addPassenger(p2);        
        company.addVehicle(taxi1);
        company.requestPickup(p1);
        company.requestPickup(p2);
        taxi1.setLocation(p1.getPickup());
        company.arrivedAtPickup(taxi1);
        taxi1.setLocation(p1.getDestination());
        company.arrivedAtDestination(taxi1, p1);
        assertEquals(p2.getPickup(), taxi1.getTargetLocation());

        company2.addPassenger(p2);
        company2.addPassenger(p3);
        company2.addVehicle(taxi2);
        company2.requestPickup(p2);
        company2.requestPickup(p3);
        taxi2.setLocation(p2.getPickup());
        company2.arrivedAtPickup(taxi2);
        taxi2.setLocation(p2.getDestination());
        company2.arrivedAtDestination(taxi2, p2);
        assertEquals(p3.getPickup(), taxi2.getTargetLocation());
    }
}
