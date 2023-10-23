import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test implementation of the Location class.
 * 
 * @author Jose, Manuel & David
 * @version 2023.10.10
 */
public class LocationTest
{
    Location startLocation;
    Location destination;
    Location startLocation2;
    
    /**
     * Default constructor for test class LocationTest
     */
    public LocationTest()
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
        startLocation = new Location(1, 2);
        startLocation2 = new Location(5,5);
        destination = new Location(2, 2);
    }

    /**
     * Test the distance method of the Location class.
     */
    @Test
    public void testDistance()
    {
        assertEquals(startLocation.distance(new Location(5, 7)), 5);
        assertEquals(startLocation.distance(destination), 1);
        assertTrue(startLocation.distance(destination) == 1);
    }

    /**
     * Run tests of the nextLocation method of the Location class.
     */
    @Test
    public void testAdjacentLocations()
    {
        assertEquals(startLocation2.nextLocation(destination), new Location(4,4));
        assertEquals(startLocation.nextLocation(startLocation2), new Location(2,3));
        assertEquals(startLocation.nextLocation(destination), destination);
        assertEquals(destination.nextLocation(destination), destination);
    }
}
