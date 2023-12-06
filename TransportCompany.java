import java.util.*;
import javafx.util.Pair;

/**
 * Model the operation of a taxi company, operating different
 * types of vehicle. This version operates a single taxi.
 * 
 * @author David, Manuel and Jose
 * @version 2023.10.23
 */
public class TransportCompany  
{
    private String name;  // name of the transport company
    private List <Taxi> vehicles; //taxi list
    private List <Passenger> passengers; //passengers list
    private LinkedHashMap<Taxi, List<Passenger>> assignments; //taxi and passenger
                                                      //assignments
    
    /**
     * Constructor for objects of class TransportCompany
     * @param name The name of the TransportCompany
     */
    public TransportCompany(String name)
    {
        this.name = name;
        this.vehicles = new ArrayList <>();
        this.passengers = new ArrayList <>();
        this.assignments = new LinkedHashMap();
    }

    /**
     * @return The name of the company.
     */
    public String getName()
    {
        return name;
    }

    /**
     * A vehicle has arrived at a passenger's destination.
     * @param vehicle The vehicle at the destination.
     * @param passenger The passenger being dropped off.
     */
    public void arrivedAtDestination(Taxi vehicle,
    Passenger passenger)
    {
        System.out.println(">>>> " + vehicle + " offloads " + passenger);
        passenger.act();
        
        List<Passenger> pAux = assignments.get(vehicle);
        if(pAux != null){
            vehicle.setTargetLocation(pAux.get(0).getPickup());
        }
    }

    /**
     * @return The list of vehicles.
     */
    public List<Taxi> getVehicles()
    {             
        return vehicles;
    }

    /**
     * @return The list of passengers.
     */
    public List<Passenger> getPassengers()
    {
        return passengers;
    }

    /**
     * @param Add the new Vehicle.
     */
    public void addVehicle(Taxi vehicle)
    {
        vehicles.add(vehicle);
    }

    /**
     * Add a new passenger in the company.
     * @param passenger The new passenger.
     */
    public void addPassenger(Passenger passenger)
    {
        passengers.add(passenger);
        Collections.sort(passengers, new ComparadorNombrePassenger());
    }

    /**
     * Find a the most closed free vehicle to a location, if any.
     * @param location location to go
     * @return A free vehicle, or null if there is none.
     */
    private Taxi scheduleVehicle(Location location, int money)
    {
        Taxi vh = null;
        
        boolean enc = false;
        
        ComparadorTaxiLocation comp = new ComparadorTaxiLocation();
        comp.setLocationDestination(location);
        Collections.sort(vehicles, comp);
        
        
        Iterator <Taxi> it = vehicles.iterator();
       
        while(it.hasNext() && !enc){
            vh = it.next();

            if(vh.isFree()){
                if(money > 20000){  
                    if(vh.getOccupation() == 1){
                        enc = true;
                    }
                }
                else {
                    if (vh.getOccupation() > 1 && vh.getOccupation() < 5)
                        enc = true;
                }
            }
        }
        
        return vh;
    }

    /**
     * Request a pickup for the given passenger.
     * @param passenger The passenger requesting a pickup.
     * @return Whether a free vehicle is available.
     */
    public boolean requestPickup(Passenger passenger)
    {
        Taxi taxi = scheduleVehicle(passenger.getPickup(),
                                    passenger.getCreditCard());
        List<Passenger> assignedPassengers;
        
        boolean result = false;
        if(taxi != null){
            if(assignments.containsKey(taxi)){
                assignedPassengers = assignments.remove(taxi);
            }
            else{
                assignedPassengers = new ArrayList<Passenger>();
            }
            assignedPassengers.add(passenger);
            Collections.sort(assignedPassengers, new ComparadorArrivalTime());
            taxi.setPickupLocation(passenger.getPickup());
            assignments.put(taxi, assignedPassengers);
            result = true;
            System.out.println("<<<< " + taxi + " go to pick up passenger " 
                + passenger.getName() + " at " + passenger.getPickup());
        }

        return result;
    }

    /**
     * A vehicle has arrived at a pickup point.
     * @param vehicle The vehicle at the pickup point.
     */
    public void arrivedAtPickup(Taxi taxi)
    {

        
        Passenger pAux = assignments.get(taxi).get(0);
        
        taxi.pickup(pAux);
        assignments.get(taxi).remove(pAux);
        // Iterator<Map.Entry<Taxi, List<Passenger>>> it = assignments.entrySet().iterator();
        // Map.Entry<Taxi, List<Passenger>> aux = null;
    
        // boolean enc = false;
        // while(it.hasNext() && !enc){
            
            // aux = it.next();
            
            // Taxi tAux = aux.getKey();
            // if(tAux.equals(taxi)) {
                // enc = true;
                // Passenger passenger = aux.getValue().get(0);
                // it.remove();
                // tAux.pickup(passenger);
                // passenger.setTaxiName(tAux.getName());
                // System.out.println("<<<< " + taxi + " picks up " + passenger.getName());
            // }       
        // }
    }
    
    public void showFinalInfo()
    {
        Collections.sort( vehicles, new ComparadorTaxiInactivo());
        Taxi aux = vehicles.get(0);
        int idle = aux.getIdleCount();
        System.out.println("-->> Taxi(s) with less time not active <<--");
        System.out.println(aux.showFinalInfo());
        
        boolean enc = false;
        for(int i = 1; i < vehicles.size() && !enc; i++){
            aux= vehicles.get(i);
            if(aux.getIdleCount() == idle){
                System.out.println(aux.showFinalInfo());
                enc = true;
            }
        }  
        
        Collections.sort( vehicles, new ComparadorTaxiValuation());
        aux = vehicles.get(0);
        int value = aux.getValuation();
        System.out.println("-->> Taxi(s) with highest evaluation <<--");
        System.out.println(aux.showFinalInfo());      
        enc = false;
        for(int i = 1; i < vehicles.size() && !enc; i++){
            aux= vehicles.get(i);
            if(aux.getValuation() == value){
                System.out.println(aux.showFinalInfo());
                enc = true;
            }
        }           
    }

}