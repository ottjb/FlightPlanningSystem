package Managers;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

import Objects.Airport;

public class AirportManager {

    private Vector<Airport> airports;

    public AirportManager() {
        this.airports = new Vector<Airport>();

        try {
            Scanner scanner = new Scanner(new File("data/airports.txt"));
            while (scanner.hasNextLine()) {
                String temp = scanner.nextLine();
                StringTokenizer st = new StringTokenizer(temp, ";");
                String temp2 = st.nextToken();
                StringTokenizer st2 = new StringTokenizer(temp2, "|");
                String[] airportData = new String[7];
                for(int i = 0; i < airportData.length; i++) {
                    airportData[i] = st2.nextToken();
                }
                String[] fuelTypes = {airportData[5], airportData[6]};
                Airport airport = new Airport(airportData[0], airportData[1], 
                Double.parseDouble(airportData[2]), Double.parseDouble(airportData[3]), 
                Double.parseDouble(airportData[4]), fuelTypes);

                this.airports.add(airport);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public void add(Airport airport) {
        
    }

    public void delete(Airport airport) {
        
    }

    public void edit(Airport airport) {
        
    }

    public void display(Airport airport) {
        
    }

    // This is a test method, this will not be included in the final version
    public void displayAirports() {
        for (Airport airport : this.airports) {
            System.out.println(airport.getICAOIdentifier() + " " + airport.getName());
        }
    }

    public Vector<Airport> search(String search) {
        
        return new Vector<Airport>();
    }

    public void displayMenu() {
        System.out.println("Please select an option:");
        System.out.println("1. Add an airport");
        System.out.println("2. Delete an airport");
        System.out.println("3. Edit an airport");
        System.out.println("4. Display an airport");
        System.out.println("5. Cancel");
    }
}
