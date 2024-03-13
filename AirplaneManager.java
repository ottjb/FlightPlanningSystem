import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

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

    }

    public void edit(Airplane airplane) {

    }

    public void display(Airplane airplane) {

    }

    // This is a test method, this will not be included in the final version
    public void displayAirplanes() {
        for (Airplane airplane : this.airplanes) {
            System.out.println(airplane.getMake() + " " + airplane.getModel());
        }
    }

    public Vector<Airplane> search(String search) {

        return new Vector<Airplane>();
    }

    public void displayMenu() {
        Scanner in = new Scanner(System.in);
        Utility utility = new Utility();
        System.out.println();
        System.out.println("Airplane Management");
        System.out.println("1. Add an airplane");
        System.out.println("2. Delete an airplane");
        System.out.println("3. Edit an airplane");
        System.out.println("4. Display an airplane");
        System.out.println("5. Cancel");
        int option = utility.getIntegerInput();

        switch (option) {
            case 1:
                String[] options = {"Enter the make of the airplane:", "Enter the model of the airplane:", "Enter the type of the airplane:", "Enter the fuel tank size of the airplane:", "Enter the fuel burn of the airplane:", "Enter the air speed of the airplane:"};
                String[] inputs = new String[6];
                System.out.println("Add an airplane");
                for (int i = 0; i < options.length; i++) {
                    System.out.println(options[i]);
                    if (i == 3 || i == 4 || i == 5) {
                        inputs[i] = Double.toString(utility.getDoubleInput());
                    } else {
                        inputs[i] = utility.getStringInput();
                    }
                }
                this.add(new Airplane(inputs[0], inputs[1], inputs[2], inputs[3], inputs[4], inputs[5]));
                this.displayAirplanes();
                break;
            case 2:
                System.out.println("Delete an airplane");
                break;
            case 3:
                System.out.println("Edit an airplane");
                break;
            case 4:
                System.out.println("Display an airplane");
                break;
            case 5:
                utility.close();
                break;
            default:
                System.out.println("Please select a valid option.");
                break;
        }
        in.close();
    }
}
