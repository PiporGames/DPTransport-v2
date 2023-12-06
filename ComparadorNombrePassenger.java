import java.util.*; 

/**
 * Compare passengers by name in ascending order.
 * 
 * @author José, Manuel y David
 * @version 2023
 */
public class ComparadorNombrePassenger implements Comparator<Passenger>
{
    public int compare(Passenger p1, Passenger p2){  
        return (p1.getName().compareTo(p2.getName()));
    } 
}
