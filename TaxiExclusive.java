
/**
 * Write a description of class TaxiExclusive here.
 *
 * @author (your name)
 * @version (a version number or a date)
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
