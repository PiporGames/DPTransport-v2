import java.util.*; 

/**
 * Compare taxis by name in ascending order.
 * 
 * @author José, Manuel y David   
 * @version  2023
 */
public class ComparadorTaxiNombre implements Comparator<Taxi>
{       
    public int compare(Taxi t1, Taxi t2){  
        return (t1.getName().compareTo(t2.getName()));
    } 
}