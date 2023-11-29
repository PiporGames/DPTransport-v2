import java.util.*; 

/**
 * Compares Taxis by their distance to a certain location.
 * If two of them are equally distant to the specified location,
 * their names are used to compare instead.
 * 
 * @author José, Manuel y David
 * @version 2023.11.1
 */
public class ComparadorArrivalTime implements Comparator<Passenger>
{       
    public int compare(Passenger p1, Passenger p2){  
        int aux1 = p1.getArrivalTime();
        int aux2 = p2.getArrivalTime();
        if(aux1 == aux2){
            return (p1.getName().compareTo(p2.getName())); 
        }
        else if(aux1 < aux2){
            return -1;            
        }
        else {
            return 1;
        }
    } 
}