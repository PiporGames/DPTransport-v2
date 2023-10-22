import java.util.*; 

/**
 * Write a description of class ComparadorTaxiLocation here.
 * 
 * @author (your name)     
 * @version (a version number or a date)
 */
public class ComparadorTaxiLocation implements Comparator<Taxi>
{       
    private Location destination;
     
    public void setLocationDestination(Location location){
        destination = location;
    }
    
    public int compare(Taxi t1, Taxi t2){  
        int d1 = t1.getLocation().distance(destination);
        int d2 = t2.getLocation().distance(destination);
        if(d1 == d2){
            return (t1.getName().compareTo(t2.getName())); 
        }
        else if(d1 < d2){
            return -1;            
        }
        else {
            return 1;
        }
    } 
}