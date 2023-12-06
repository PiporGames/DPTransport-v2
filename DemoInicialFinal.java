import java.util.*;
import java.io.*;

/**
 * Provide a simple demonstration of running a stage-one
 * scenario. Two passengers and three taxis are created. Two pickups
 * requested. As the simulation is run, the passengers
 * should be picked up and then taken to their destination.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 * @version 2023 DP Classes
 */
public class DemoInicialFinal
{
    TransportCompany company;
    private List<Taxi> actors;

    /**
     * Constructor for objects of class DemoOnePassanger
     */
    public DemoInicialFinal()
    {
        company = new TransportCompany("Compañía Taxis Cáceres");
        actors = new ArrayList<>();
        reset();
    }

    /**
     * Run the demo for a fixed number of steps.
     */
    public void run()
    {
        //Ejecutamos un número de pasos la simulación.
        //En cada paso, cada taxi realiza su acción
        for(int step = 0; step < 100; step++) {
            step();
        }
        showFinalInfo();
    }

    /**
     * Run the demo for one step by requesting
     * all actors to act.
     */
    public void step()
    {
        for(Taxi actor : actors) {
            actor.act();
        }
    }

    /**
     * Reset the demo to a starting point.
     * A single taxi and passenger are created, and a pickup is
     * requested for a single passenger.
     * @throws IllegalStateException If a pickup cannot be found
     */
    public void reset()
    {
        actors.clear();

        createTaxis();
        createPassengers(); 
        showInicialInfo();
        runSimulation();
    }

    /**
     * Taxis are created and added to the company
     */
    private void createTaxis() {
        Taxi taxi1 = new TaxiExclusive(company, new Location(3, 3),"T2", FuelComsumption.MEDIUM, 7000);
        Taxi taxi2 = new TaxiShuttle(company, new Location(10,10),"T1", FuelComsumption.LOW, 2);
        Taxi taxi3 = new TaxiExclusive(company, new Location(15, 15),"T3", FuelComsumption.HIGH, 9000);
        company.addVehicle(taxi1);
        company.addVehicle(taxi2);
        company.addVehicle(taxi3);
        actors.addAll(company.getVehicles());
    }

    /**
     * Passengers are created and added to the company
     */
    private void createPassengers() {
        Passenger passenger1 = new PassengerVip(new Location(0, 0),
                new Location(2, 6),"Lucy", 30, 30000, Reliability.HIGH);
        Passenger passenger2 = new PassengerNoVip(new Location(6, 6),
                new Location(5,2),"Gru", 20, 3000, Reliability.LOW);
        Passenger passenger3 = new PassengerNoVip(new Location(10, 4),
                new Location(14,2),"Kevin", 20, 2000, Reliability.LOW);

        company.addPassenger(passenger1);
        company.addPassenger(passenger2);
        company.addPassenger(passenger3);
    }

    /**
     * A pickup is requested for a single passenger.
     * @throws IllegalStateException If a pickup cannot be found
     */
    private void runSimulation() {
        List<Passenger> passengers = company.getPassengers();
        for(Passenger passenger : passengers) {
            if(!company.requestPickup(passenger)) {
                throw new IllegalStateException("Failed to find a pickup for: "+ passenger.getName());        
            }
        }
    }

    /**
     * Initial info is showed with the information about taxis and passengers
     */
    private void showInicialInfo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))){
            List<Taxi> vehicles = company.getVehicles();
            List<Passenger> passengers = company.getPassengers();
            Collections.sort(vehicles, new ComparadorTaxiNombre());
            Collections.sort(passengers, new ComparadorNombrePassenger());
            System.out.println("--->> Simulation of the company: "+company.getName()+" <<---");
            writer.write("--->> Simulation of the company: "+company.getName()+" <<---");
            writer.newLine();
            System.out.println("-->> Taxis of the company <<--");
            writer.write("-->> Taxis of the company <<--");
            writer.newLine();
    
            for(Taxi  taxi : vehicles) {
                System.out.println(taxi);
                writer.write(taxi.toString());
                writer.newLine();
            }
            System.out.println("-->> Passengers requesting taxi <<--");
            writer.write("-->> Passengers requesting taxi <<--");
            writer.newLine();
            Collections.sort(passengers, new ComparadorArrivalTime());
    
            for(Passenger passenger : passengers) {
                System.out.println(passenger);
                writer.write(passenger.toString());
                writer.newLine();
            }
            System.out.println("");
            writer.newLine();
            System.out.println("-->> ---------------- <<--");
            writer.write("-->> ---------------- <<--");
            writer.newLine();
            System.out.println("-->> Simulation start <<--");
            writer.write("-->> Simulation start <<--");
            writer.newLine();
            System.out.println("-->> ---------------- <<--");
            writer.write("-->> ---------------- <<--");
            writer.newLine();
            System.out.println("");
            writer.newLine();
            
            writer.close();
        }
        catch (IOException e) {
            System.err.println("There was a problem writing to output.txt");
        }
    }

    /**
     * Final info is showed with the information about taxis and passengers
     */
    private void showFinalInfo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            List<Taxi> vehicles = company.getVehicles();
            List<Passenger> passengers = company.getPassengers();
            Collections.sort(vehicles, new ComparadorTaxiPasajeros());
            Collections.sort(passengers, new ComparadorNombrePassenger());
    
            System.out.println("");
            writer.newLine();
            System.out.println("-->> ----------------- <<--");
            writer.write("-->> ----------------- <<--");
            writer.newLine();
            System.out.println("-->> End of simulation <<--"); 
            writer.write("-->> End of simulation <<--");
            writer.newLine();
            System.out.println("-->> ----------------- <<--");
            writer.write("-->> ----------------- <<--");
            writer.newLine();
            System.out.println("");
            writer.newLine();
    
            System.out.println("-->> Taxis final information <<--");
            writer.write("-->> Taxis final information <<--");
            writer.newLine();
    
            for(Taxi  taxi : vehicles) {
                System.out.println(((Taxi)taxi).showFinalInfo());
                writer.write(((Taxi)taxi).showFinalInfo());
                writer.newLine();
            }
            System.out.println("-->> Passengers final information <<--");
            writer.write("-->> Passengers final information <<--");
            writer.newLine();
            for(Passenger passenger : passengers) {
                System.out.println(passenger.showFinalInfo());
                writer.write(passenger.showFinalInfo());
                writer.newLine();
            }
            //Muestra los Taxis con menos turnos inactivos y Taxis con más valoración
            company.showFinalInfo();
            
            writer.close();
        }
        catch(IOException e) {
            System.err.println("There was a problem writing to output.txt");
        }
    }

}
