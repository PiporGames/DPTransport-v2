/**
 * Model a passenger wishing to get from one
 * location to another.
 * 
 * @author Jose, Manuel & David
 * @version 2023.11.27
 */
public abstract class Passenger
{
    private String name;
    private Location pickup;
    private Location destination;
    private String taxiName;
    private int arrivalTime;
    private int creditCard;
    private Reliability reliable;

    /**
     * Constructor for objects of class Passenger
     * @param pickup The pickup location, must not be null.
     * @param destination The destination location, must not be null.
     * @param name The passenger's name
     * @param arrivalTime The time the passenger arrived at his destination.
     * @param creditCard The money the Passenger has
     * @param reliable The reliability of the Passenger
     * @throws NullPointerException If either location is null.
     */
    public Passenger(Location pickup, Location destination, String name, int arrivalTime, int creditCard, Reliability reliable)
    {
        if(pickup == null) {
            throw new NullPointerException("Pickup location");
        }
        if(destination == null) {
            throw new NullPointerException("Destination location");
        }
        this.pickup = pickup;
        this.destination = destination;
        this.name = name;
        this.taxiName = null;
        this.arrivalTime = arrivalTime;
        this.creditCard = creditCard;
        this.reliable = reliable;
    }
    
    /**
     * Pays an amount of money to the cab, decreasing the credit card score.
     */
    public abstract void pay();
    
    /**
     * @return The score the Passenger is going to rate to the cab.
     */
    public int calculateEvaluationValue(){
        return(2 * reliable.getValor());
    }
    
    /**
     * @return The arrival time
     */
    public int getArrivalTime() {
        return (arrivalTime);
    }
    
    /**
     * Does several actions, such as pay() and calculateEvauluationValue()
     */
    public int act(){
        pay();
        int valoration = calculateEvaluationValue();
        return (valoration);
    }
    
    /**
     * @return The name of the passenger.
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return The destination location.
     */
    public Location getDestination()
    {
        return destination;
    }
    
    /**
     * Return details of the passenger, such as where it is.
     * @return A string representation of the passenger.
     */
    public String toString()
    {
        return "Passenger "+getName()+" travelling from " + pickup + " to " + destination + " arrival time: " + arrivalTime + " money in the credit card: " + creditCard + " " + reliable;
    }
    
    /**
     * @return The pickup location
     */    
    public Location getPickup() 
    {   
        return pickup;
    }
    
    /**
     * Sets the name of the taxi.
     * @param taxiName The taxi's name
     */    
    public void setTaxiName(String taxiName)
    {
        this.taxiName = taxiName;
    }
    
    /**
     * @return The taxi's name
     */  
    public String getTaxiName()
    {
        return taxiName;
    }
    
    /**
     * @return The current reliability of the Passenger
     */
    protected Reliability getReliability(){
        return reliable;
    }
    
    /**
     * @return The current amount of money in the credit card.
     */
    public int getCreditCard(){
        return creditCard;
    }

    /**
     * Sets the credit card value.
     * @param value The credit card new value
     */
    protected void setCreditCard(int value){
        creditCard = value;
    }
    
    /**
     * Show the final information about the passenger, including the name of the taxi that used.
     */
    public String showFinalInfo()
    {
        return "Passenger " + getName() + " in " + getDestination() +  " transported by: " + getTaxiName() + " with " + creditCard + " money in the credit card";
    }

}
