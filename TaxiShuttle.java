import java.util.*;

/**
 * Write a description of class TaxiShuttle here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TaxiShuttle extends Taxi
{
    public TaxiShuttle(TransportCompany company, Location location, 
                        String name, FuelComsumption comsumption, 
                        int occupation)
    {
        super(company, location, name, comsumption, occupation);
    }
    
    @Override
    public int obtainComsumption()
    {
        return (getComsumption() 
                * getLocation().distance(getInitialLocation()));
    }
}
