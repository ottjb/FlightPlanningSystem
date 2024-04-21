import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JLabel;

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
                for (int i = 0; i < airportData.length; i++) {
                    airportData[i] = st2.nextToken();
                }
                String[] fuelTypes = { airportData[5], airportData[6] };
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
        this.airports.add(airport);
    }

    public void delete(Airport airport) {
        this.airports.remove(airport);
    }

    public void edit(Airport airport, String ICAOIdentifier, String name, double latitude, double longitude, double COMFrequency, String[] fuelType) {
        airport.setICAOIdentifier(ICAOIdentifier);
        airport.setName(name);
        airport.setLatitude(latitude);
        airport.setLongitude(longitude);
        airport.setCOMFrequency(COMFrequency);
        airport.setFuelType(fuelType);
    }

    public void display(Airport airport) {
        JDialog dialog = new JDialog();
        dialog.setTitle(airport.getICAOIdentifier());
        dialog.setSize(230, 230);
        dialog.setLayout(null);
        dialog.setResizable(false);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(FlightPlanningSystemGUIV2.getWindows()[0]);

        JLabel ICAOLabel = new JLabel("ICAO: " + airport.getICAOIdentifier());
        ICAOLabel.setBounds(10, 10, 200, 20);
        JLabel nameLabel = new JLabel("Name: " + airport.getName());
        nameLabel.setBounds(10, 40, 200, 20);
        JLabel latitudeLabel = new JLabel("Latitude: " + airport.getLatitude() + " N");
        latitudeLabel.setBounds(10, 70, 200, 20);
        JLabel longitudeLabel = new JLabel("Longitude: " + airport.getLongitude() + " W");
        longitudeLabel.setBounds(10, 100, 200, 20);
        JLabel comFreqLabel = new JLabel("COM Frequency: " + airport.getCOMFrequency());
        comFreqLabel.setBounds(10, 130, 200, 20);
        JLabel fuelTypesLabel = new JLabel("Fuel Types: " + airport.getFuelType()[0] + ", " + airport.getFuelType()[1]);
        fuelTypesLabel.setBounds(10, 160, 200, 20);

        dialog.add(ICAOLabel);
        dialog.add(nameLabel);
        nameLabel.setToolTipText(airport.getName());
        dialog.add(latitudeLabel);
        dialog.add(longitudeLabel);
        dialog.add(comFreqLabel);
        dialog.add(fuelTypesLabel);

        dialog.setVisible(true);
    }

    public String[][] getAirportICAONameStrings() {
        String[][] ICAONameStrings = new String[this.airports.size()][2];
        for (int i = 0; i < this.airports.size(); i++) {
            ICAONameStrings[i][0] = this.airports.get(i).getICAOIdentifier();
            ICAONameStrings[i][1] = this.airports.get(i).getName();
        }
        return ICAONameStrings;
    }

    public Airport getAirport(int index) {
        return this.airports.get(index);
    }

    public int getAirportCount() {
        return this.airports.size();
    }

    public Vector<Airport> search(String search) {

        return new Vector<Airport>();
    }

    public void save() {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/airports.txt"));
            for(Airport airport : this.airports) {
                String[] fuel = airport.getFuelType();
                writer.write(airport.getICAOIdentifier() + "|" + airport.getName() + "|" + airport.getLatitude() + "|" + airport.getLongitude() + "|" + airport.getCOMFrequency() + "|" + fuel[0] + "|" + fuel[1] + ";");
                writer.newLine();
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Error saving airports");
            e.printStackTrace();
        }
    }

    public boolean isValidLongitude(double longitude) {
        return longitude >= -180 && longitude <= 180;
    }

    public boolean isValidLatitude(double latitude) {
        return latitude >= -90 && latitude <= 90;
    }

    public boolean alreadyExists(String ICAOIdentifier, String name) {
        for (Airport airport : this.airports) {
            if (airport.getICAOIdentifier().equals(ICAOIdentifier) || airport.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
