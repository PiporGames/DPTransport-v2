import java.util.*;

/**
 * Model the common elements of taxis and shuttles.
 * 
 * @author David, Jose and Manuel
 * @version 2023.10.10 DP classes 
 */
public abstract class Taxi 
{
    // The Taxi Company of this Taxi.
    private TransportCompany company;
    // Where the taxi was created.
    private Location initialLocation;
    // Where the vehicle is.
    private Location location;
    // Where the vehicle is headed.
    private  Location targetLocation;
    // Record how often the vehicle has nothing to do.
    private int idleCount;
    // Name of the taxi.
    private String name;
    // Passengers waiting for the taxi.
    private Set<Passenger> passengers;
    // Number of passengers transported.
    private int passengersTransported;
    // Average comsumption of fuel of the taxi
    private FuelComsumption fuelComsumption;
    // Valuation of the taxi
    private int valuation;
    // Represents the number of people that fits in the taxi
    private int occupation;

    /**
     * Constructor of class Vehicle
     * @param company The taxi company. Must not be null.
     * @param location The vehicle's starting point. Must not be null.
     * @param name The name of the vehicle.
     * @throws NullPointerException If company or location is null.
     */
    public Taxi(TransportCompany company, Location location, String name,
                FuelComsumption comsumption, int occupation)
    {
        if(company == null) {
            throw new NullPointerException("company");
        }
        if(location == null) {
            throw new NullPointerException("location");
        }
        this.company = company;
        initialLocation = location;
        this.location = location;
        targetLocation = null;
        idleCount = 0;
        this.name = name;
        passengers = new TreeSet<>(new ComparadorArrivalTime());
        passengersTransported = 0;
        fuelComsumption = comsumption;
        valuation = 0;
        this.occupation = occupation;
    }

    /**
     * @return the name of the taxi
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gets the location.
     * @return Where this taxi is currently located.
     */
    public Location getLocation()
    {
        return location;
    }
    
    /**
     * Get the initial location.
     * @return Where this taxi was created.
     */
    public Location getInitialLocation()
    {
        return initialLocation;
    }    

    /**
     * Get the comsumption of the taxi.
     * @returns The comsumption.
     */
    public int getComsumption()
    {
        return fuelComsumption.getComsumption();
    }
    
    /**
     * Set the current location.
     * @param location Where it is. Must not be null.
     * @throws NullPointerException If location is null.
     */
    public void setLocation(Location location)
    {
        if(location != null) {
            this.location = location;
        }
        else {
            throw new NullPointerException();
        }
    }

    /**
     * Get the target location.
     * @return Where this vehicle is currently headed, or null
     *         if it is idle.
     */
    public Location getTargetLocation()
    {
        return targetLocation;
    }

    /**
     * Set the required target location.
     * @param location Where to go. Must not be null.
     * @throws NullPointerException If location is null.
     */
    public void setTargetLocation(Location location)
    {
        if(location != null) {
            targetLocation = location;
        }
        else {
            throw new NullPointerException();
        }
    }

     /**
     * Receive a pickup location. This becomes the
     * target location.
     * @param location The pickup location.
     */
    public void setPickupLocation(Location location)
    {
        setTargetLocation(location);
    }
    
    /**
     * Has the vehicle a target Location?
     * @return Whether or not this vehicle has a target Location.
     */
    public boolean hasTargetLocation(){
        return getTargetLocation() != null;
    }
    
    /**
     * Clear the target location.
     */
    public void clearTargetLocation()
    {
        targetLocation = null;
    }

    /**
     * @return on how many steps this vehicle has been idle.
     */
    public int getIdleCount()
    {
        return idleCount;
    }

    /**
     * Increment the number of steps on which this vehicle
     * has been idle.
     */
    public void incrementIdleCount()
    {
        idleCount++;
    }

    /**
     * Return details of the taxi, such as where it is.
     * @return A string representation of the taxi.
     */
    public String toString()
    {
        return getClass().getName() + " " + getName() + " at " + getLocation();
    }

    /**
     * Is the taxi free?
     * @return Whether or not this taxi is free.
     */
    public boolean isFree()
    {
        return targetLocation == null;
    }

    /**
     * Notify the company of our arrival at a pickup location.
     */
    public void notifyPickupArrival()
    {
        company.arrivedAtPickup(this);
    }

    /**
     * Notify the company of our arrival at a passenger's destination.
     * @param passenger The passenger inside the taxi
     */
    public void notifyPassengerArrival(Passenger passenger)
    {
        offloadPassenger(passenger);
        company.arrivedAtDestination(this, passenger);
    }

    /**
     * Receive a passenger.
     * Set passenger's destination as its target location.
     * @param passenger The passenger.
     */
    public void pickup(Passenger passenger)
    {
        passengers.add(passenger);
    }

    /**
     * Offload the passenger.
     */
    public void offloadPassenger(Passenger passenger)
    {
        valuation += passenger.act();
        passengers.remove(passenger);
        targetLocation= null;
    }

    /**
     * @return how many passengers this vehicle has transported.
     */
    public int getPassengersTransported()
    {
        return passengersTransported;
    }
    
    /**
     * Increment the number of passengers this vehicle has transported.
     */
    protected void incrementPassengersTransported()
    {
        passengersTransported++;
    }

    /**
     * Get the distance to the target location from the current location.
     * @return distance to target location.
     */
    public int distanceToTheTargetLocation()
    {
        return location.distance(targetLocation);
    }

    /**
     * Carry out a taxi's actions.
     */
    public void act()
    {
        if(targetLocation == null) {
            idleCount++;
        } else {
            location = location.nextLocation(targetLocation);
            System.out.println("@@@  Taxi: " + name + " moving to: " 
                + location.getX() + " - " + location.getY());    
            if(location.equals(targetLocation)) {
                if(passengers.isEmpty()) {
                    notifyPickupArrival();
                } else {
                    notifyPassengerArrival((Passenger) 
                                            passengers.toArray()[0]);
                    incrementPassengersTransported();
                }
            }
        }
    }
    
    /**
     * Calculates the total comsumption of the taxi
     * @return The total comsumption
     */
    public abstract int obtainComsumption();
    
     /**
     * Return details of the taxi, such as where it is.
     * @return A string representation of the taxi.
     */
    public String showFinalInfo()
    {
        String info = "Taxi " + name + " at " + location + " - " +
                    "passengers transported: " + passengersTransported + " - " +
                    "non active for: " + idleCount + " times";
        return info;

    }

}
