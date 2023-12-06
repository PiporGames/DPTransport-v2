
/**
 * Modelate a taxi that brings people around.
 * It is a exclusive version of the taxi.
 *
 * @author José, Manuel y David
 * @version 2023
 */
public class TaxiExclusive extends Taxi implements SerPopularEnRedes
{
    private int weight;
    private int popularity;
    
    public TaxiExclusive(TransportCompany company, Location location, 
                        String name, FuelComsumption comsumption, int weight)
    {
        super(company, location, name, comsumption, 1);
        this.weight = weight;
        popularity = 6;
    }
    
    @Override
    public void updatePopularity(int money)
    {
        if (money > 20000) {
            popularity += 4;
        } else {
            popularity--;
        }
    }
    
    @Override
    public void offloadPassenger()
    {
        Passenger passenger = (Passenger) getPassengers().toArray()[0];
        super.offloadPassenger();
        updatePopularity(passenger.getCreditCard());
    }
    
    @Override
    public int obtainComsumption()
    {
        return (weight/2 * getComsumption() 
                * getLocation().distance(getInitialLocation()));        
    }
    
    @Override
    public String showFinalInfo()
    {
        return(super.showFinalInfo() + " - popularity: " + popularity);
    }
}
