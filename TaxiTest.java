import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

/**
 * The test class TaxiTest.
 *
 * @author  Jose, Manuel & David
 * @version 2023.10.25 DP classes 
 */
public class TaxiTest
{
    private TransportCompany company;
    private TransportCompany company2;
    private Taxi taxi;
    private Taxi taxi2;
    private Passenger passenger;
    private Passenger passenger2;
    private Passenger passenger3;
    
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
        company = new TransportCompany("Compañía Taxis Cáceres");
        company2 = new TransportCompany("ALSA Taxis");
        // Starting position for the taxi.
        
        Location taxiLocation = new Location(0, 0);
        
        Location pickup = new Location(1, 2);
        Location destination = new Location(5, 6);
        passenger = new PassengerNoVip(pickup, destination,"Kevin", 1, 12000, Reliability.LOW);
        taxi = new TaxiShuttle(company, taxiLocation,"T1", FuelComsumption.MEDIUM, 4);
     
        Location taxiLocation2 = new Location(1, 1);  
        Location pickup2 = new Location(2, 2);
        Location destination2 = new Location(4, 4);
        passenger2 = new PassengerVip(pickup2, destination2,"Clara", 1, 29000, Reliability.HIGH);
        taxi2 = new TaxiExclusive(company2, taxiLocation2,"T2",FuelComsumption.HIGH, 7000);
        
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
        assertEquals(null ,taxi.getTargetLocation());
        assertEquals(0,taxi.getIdleCount());
        assertEquals(0,taxi.getValuation());
        assertEquals(0,taxi.getPassengersTransported());
        assertEquals(new Location(0,0),taxi.getInitialLocation());
        assertEquals(FuelComsumption.MEDIUM.getComsumption(),taxi.getComsumption());
        assertEquals(4,taxi.getOccupation());
        
        assertEquals(true, taxi2.isFree());
        assertEquals("T2", taxi2.getName());
        assertEquals(new Location(1,1), taxi2.getLocation());
        assertEquals(null ,taxi2.getTargetLocation());
        assertEquals(0,taxi2.getIdleCount());
        assertEquals(0,taxi2.getValuation());
        assertEquals(0,taxi2.getPassengersTransported());
        assertEquals(new Location(1,1),taxi2.getInitialLocation());
        assertEquals(FuelComsumption.HIGH.getComsumption(),taxi2.getComsumption());
        assertEquals(1,taxi2.getOccupation());
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
        taxi.setPickupLocation(passenger.getPickup());
        assertEquals(true, taxi.isFree());
        assertEquals(passenger.getPickup(), taxi.getTargetLocation());
 
        taxi2.pickup(passenger2);
        taxi2.setPickupLocation(passenger2.getPickup());
        assertEquals(false, taxi2.isFree());
        assertEquals(passenger2.getPickup(), taxi2.getTargetLocation());
    }

    /**
     * Test that a taxi becomes free again after offloading
     * a passenger.
     */
    @Test
    public void testOffload()
    {
        taxi.setPickupLocation(passenger.getPickup());
        taxi.pickup(passenger);
        assertEquals(true, taxi.isFree());
        assertEquals(passenger.getDestination(), taxi.getTargetLocation());
        
        taxi.offloadPassenger();
        assertEquals(true, taxi.isFree());
        assertEquals(null, taxi.getTargetLocation());
        
        
        
        taxi2.setPickupLocation(passenger2.getPickup());
        taxi2.pickup(passenger2);
        assertEquals(false, taxi2.isFree());
        assertEquals(passenger2.getDestination(), taxi2.getTargetLocation());
        
        taxi2.offloadPassenger();
        assertEquals(true, taxi2.isFree());
        assertEquals(null, taxi2.getTargetLocation());
    }

    /**
     * Test that a taxi picks up and delivers a passenger within
     * a reasonable number of steps.
     */
    @Test
    public void testDelivery()
    {
        company.addPassenger(passenger);
        company.addVehicle(taxi);
        company.requestPickup(passenger);
        assertEquals(passenger.getPickup(), taxi.getTargetLocation());
        // Go to pickup
        int dst = taxi.distanceToTheTargetLocation();
        for(int i = 1; i <= dst + 1; i++){
            taxi.act();
        }
        assertEquals(passenger.getDestination(), taxi.getTargetLocation());
        // Go to passenger destination
        int dst2 = taxi.distanceToTheTargetLocation();
        for(int i = 1; i <= dst2 + 1; i++){
            taxi.act();
        }
        assertEquals(null, taxi.getTargetLocation());
        
        company2.addPassenger(passenger2);
        company2.addVehicle(taxi2);
        company2.requestPickup(passenger2);
        assertEquals(passenger2.getPickup(), taxi2.getTargetLocation());
        // Go to pickup
        int dst3 = taxi2.distanceToTheTargetLocation();
        for(int i = 1; i <= dst3 + 1; i++){
            taxi2.act();
        }
        assertEquals(passenger2.getDestination(), taxi2.getTargetLocation());
        // Go to passenger destination
        int dst4 = taxi2.distanceToTheTargetLocation();
        for(int i = 1; i <= dst4 + 1; i++){
            taxi2.act();
        }
        assertEquals(null, taxi2.getTargetLocation());
    
    }
    
    /**
     * Test that the correct comsuption is retrieved from the taxi.
     */
    @Test
    public void testObtainComsumption()
    {
        company.addPassenger(passenger);
        company.addVehicle(taxi);
        company.requestPickup(passenger);
        assertEquals(0, taxi.obtainComsumption());
        // Go to pickup
        int dst = taxi.distanceToTheTargetLocation();
        for(int i = 1; i <= dst + 1; i++){
            taxi.act();
        }
        assertEquals(18, taxi.obtainComsumption());
        // Go to passenger destination
        int dst2 = taxi.distanceToTheTargetLocation();
        for(int i = 1; i <= dst2 + 1; i++){
            taxi.act();
        }
        assertEquals(36, taxi.obtainComsumption());
        
        company2.addPassenger(passenger2);
        company2.addVehicle(taxi2);
        company2.requestPickup(passenger2);
        assertEquals(0, taxi2.obtainComsumption());
        // Go to pickup
        int dst3 = taxi2.distanceToTheTargetLocation();
        for(int i = 1; i <= dst3 + 1; i++){
            taxi2.act();
        }
        assertEquals(56000, taxi2.obtainComsumption());
        // Go to passenger destination
        int dst4 = taxi2.distanceToTheTargetLocation();
        for(int i = 1; i <= dst4 + 1; i++){
            taxi2.act();
        }
        assertEquals(84000, taxi2.obtainComsumption());
    }
    
    /**
     * Test that the correct Set of Passengers are returned.
     */
    @Test
    public void testGetPassenger()
    {
        Set<Passenger> s1 = new TreeSet<>(new ComparadorArrivalTime());
        s1.add(passenger);
        taxi.pickup(passenger);
        assertEquals(s1, taxi.getPassengers());
        s1.add(passenger2);
        taxi.pickup(passenger2);
        assertEquals(s1, taxi.getPassengers());
        
        Set<Passenger> s2 = new TreeSet<>(new ComparadorArrivalTime());
        s2.add(passenger2);
        taxi2.pickup(passenger2);
        assertEquals(s2, taxi2.getPassengers());
        
    }
    
    /**
     * Test that the various types of taxis act correctly.
     */
    @Test
    public void testAct()
    {
        company.addPassenger(passenger);
        company.addVehicle(taxi);
        company.requestPickup(passenger);
        // Go to pickup
        assertEquals(new Location(0,0), taxi.getLocation());
        taxi.act();
        assertEquals(new Location(1,1), taxi.getLocation());
        taxi.act();
        assertEquals(new Location(1,2), taxi.getLocation());  
        
        company2.addPassenger(passenger2);
        company2.addVehicle(taxi2);
        company2.requestPickup(passenger2);
        // Go to pickup
        assertEquals(new Location(1,1), taxi2.getLocation());
        taxi2.act();
        assertEquals(new Location(2,2), taxi2.getLocation());
    }
}

