import java.util.*; 

/**
 * Compares Taxis by their distance to a certain location.
 * If two of them are equally distant to the specified location,
 * their names are used to compare instead.
 * 
 * @author José, Manuel y David
 * @version 2023.11.1
 */
public class ComparadorTaxiValuation implements Comparator<Taxi>
{       
    public int compare(Taxi t1, Taxi t2){  
        int p1 = t1.getValuation();
        int p2 = t2.getValuation();
        if(p1 == p2){
            return (t1.getName().compareTo(t2.getName())); 
        }
        else if(p1 < p2){
            return 1;            
        }
        else {
            return -1;
        }
    } 
}