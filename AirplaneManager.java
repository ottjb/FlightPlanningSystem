import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class AirplaneManager {

    private Vector<Airplane> airplanes;

    public AirplaneManager() {
        this.airplanes = new Vector<Airplane>();

        try {
            Scanner scanner = new Scanner(new File("data/airplanes.txt"));
            while (scanner.hasNextLine()) {
                String temp = scanner.nextLine();
                StringTokenizer st = new StringTokenizer(temp, ";");
                String temp2 = st.nextToken();
                StringTokenizer st2 = new StringTokenizer(temp2, "|");
                String[] airplaneData = new String[6];
                for(int i = 0; i < airplaneData.length; i++) {
                    airplaneData[i] = st2.nextToken();
                }

                Airplane airplane = new Airplane(airplaneData[0], airplaneData[1], airplaneData[2], airplaneData[3], airplaneData[4], airplaneData[5]);
                this.airplanes.add(airplane);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public void add(Airplane airplane) {
        this.airplanes.add(airplane);
    }

    public void delete(Airplane airplane) {
        this.airplanes.remove(airplane);
    }

    public void edit(Airplane airplane, String make, String model, String type, double fuelTankSize, double fuelBurn, double airSpeed) {
        airplane.setMake(make);
        airplane.setModel(model);
        airplane.setType(type);
        airplane.setFuelTankSize(fuelTankSize);
        airplane.setFuelBurn(fuelBurn);
        airplane.setAirSpeed(airSpeed);
    }

    public void display(Airplane airplane) {
        JDialog dialog = new JDialog();
        dialog.setTitle(airplane.getMake() + " " + airplane.getModel());
        dialog.setSize(200, 230);
        dialog.setLayout(null);
        dialog.setResizable(false);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(FlightPlanningSystemGUI.getWindows()[0]);

        JLabel makeLabel = new JLabel("Make: " + airplane.getMake());
        makeLabel.setBounds(10, 10, 200, 20);
        JLabel modelLabel = new JLabel("Model: " + airplane.getModel());
        modelLabel.setBounds(10, 40, 200, 20);
        JLabel typeLabel = new JLabel("Type: " + airplane.getType());
        typeLabel.setBounds(10, 70, 200, 20);
        JLabel fuelTankSizeLabel = new JLabel("Fuel Tank Size: " + airplane.getFuelTankSize());
        fuelTankSizeLabel.setBounds(10, 100, 200, 20);
        JLabel fuelBurnLabel = new JLabel("Fuel Burn: " + airplane.getFuelBurn());
        fuelBurnLabel.setBounds(10, 130, 200, 20);
        JLabel airSpeedLabel = new JLabel("Air Speed: " + airplane.getAirSpeed());
        airSpeedLabel.setBounds(10, 160, 200, 20);

        dialog.add(makeLabel);
        dialog.add(modelLabel);
        dialog.add(typeLabel);
        dialog.add(fuelTankSizeLabel);
        dialog.add(fuelBurnLabel);
        dialog.add(airSpeedLabel);

        dialog.setVisible(true);
    }

    public String[][] getAirplaneMakeModelStrings() {
        String[][] airplaneData = new String[this.getAirplaneCount()][2];
        for (Airplane ap : this.airplanes) {
            int currentIndex = this.airplanes.indexOf(ap);
            airplaneData[currentIndex][0] = ap.getMake();
            airplaneData[currentIndex][1] = ap.getModel();
        }
        return airplaneData;
    }

    public int getAirplaneCount() {
        return this.airplanes.size();
    }

    public Airplane getAirplane(int index) {
        return this.airplanes.get(index);
    }

    public void save() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/airplanes.txt"));
            for (Airplane airplane : this.airplanes) {
                writer.write(airplane.getMake() + "|" + airplane.getModel() + "|" + airplane.getType() + "|" + airplane.getFuelTankSize() + "|" + airplane.getFuelBurn() + "|" + airplane.getAirSpeed() + ";");
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public boolean alreadyExists(String make, String model) {
        for (Airplane airplane : this.airplanes) {
            if (airplane.getMake().equals(make) && airplane.getModel().equals(model)) {
                return true;
            }
        }
        return false;
    }

    public String findCardinalDirection(double heading) {
        if (heading >= 337.5 || heading < 22.5) {
            return "N";
        } else if (heading >= 22.5 && heading < 67.5) {
            return "NE";
        } else if (heading >= 67.5 && heading < 112.5) {
            return "E";
        } else if (heading >= 112.5 && heading < 157.5) {
            return "SE";
        } else if (heading >= 157.5 && heading < 202.5) {
            return "S";
        } else if (heading >= 202.5 && heading < 247.5) {
            return "SW";
        } else if (heading >= 247.5 && heading < 292.5) {
            return "W";
        } else {
            return "NW";
        }
    }
}
