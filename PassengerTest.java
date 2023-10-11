import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class PassengerTest.
 *
 * @author  David J. Barnes and Michael Kölling
 * @version 2016.02.29
 * @version 2023.10.10 DP classes 
 */
public class PassengerTest
{           
    Passenger p1;
    
    /**
     * Default constructor for test class PassengerTest
     */
    public PassengerTest()
    {
        
    }

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
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
        
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
        
        //TODO implementar este método
        // Testear la creación correcta de objetos de tipo Passenger comprobando 
        // que la inicialización de campos como dirección de recogida y destino es correcta.
    }

    /**
     * Test of the getTaxiName method.
     * Ensure that this method gets and returns the name of the taxi correctly.
     */
    @Test
    public void testGetTaxiName()
    {
        assertEquals(p1.getTaxiName(), "T1");
        //TODO implementar este método
        // Testear el método que devuelve el nombre del taxi que ha transportado
        //al pasajero/a
    }

    /**
     * Test of the getPickupLocation method.
     * Ensure that this method gets and returns the pickup location correctly.
     */
    @Test
    public void testGetPickupLocation ()
    {
        assertEquals(p1.getPickup(), new Location(6,6));
        //TODO implementar este método
        // Testear el método que devuelve la dirección de recogida del objeto.
    }
}
