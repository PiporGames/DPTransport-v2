
/**
 * Model a passenger wishing to get from one
 * location to another.
 * The Passenger is considered VIP.
 * 
 * @author Jose, Manuel & David
 * @version 2023.11.27
 */
public class PassengerVip extends Passenger
{
    /**
     * Constructor for objects of class PassengerVIP
     * @param pickup The pickup location, must not be null.
     * @param destination The destination location, must not be null.
     * @param name The passenger's name
     * @param arrivalTime The time the passenger arrived at his destination.
     * @param creditCard The money the Passenger has
     * @param reliable The reliability of the Passenger
     * @throws NullPointerException If either location is null.
     */
    public PassengerVip(Location pickup, Location destination, String name, int arrivalTime, int creditCard, reliability reliable)
    {
        super(pickup, destination, name, arrivalTime, creditCard, reliable);
    }
    
    /**
     * @return The score the Passenger is going to rate to the cab.
     */
    @Override
    public int calculateEvaluationValue(){
        int res = getReliability().getValor();
        return(2 * res + 15);
    }
    
    /**
     * Pays an amount of money to the cab, decreasing the credit card score.
     */
    @Override
    public void pay(){
        setCreditCard(getCreditCard() - 610);
    }
}
