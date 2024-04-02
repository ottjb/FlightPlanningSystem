package Managers;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

import Objects.Airplane;

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

    public void edit(Airplane airplane) {

    }

    public void display(Airplane airplane) {
        System.out.println(airplane.getMake() + " " + airplane.getModel() + " " + airplane.getType() + " " + airplane.getFuelTankSize() + " " + airplane.getFuelBurn() + " " + airplane.getAirSpeed());
    }

    // This is a test method, this will not be included in the final version
    public void displayAirplanes() {
        for (Airplane airplane : this.airplanes) {
            System.out.println(airplane.getMake() + " " + airplane.getModel());
        }
    }

    public Vector<Airplane> search(String search) {
        Vector<Airplane> foundAirplanes = new Vector<Airplane>();
        for (Airplane airplane : this.airplanes) {
            if (airplane.getMake().contains(search) || airplane.getModel().contains(search) || airplane.getType().contains(search)) {
                foundAirplanes.add(airplane);
            }
        }
        return foundAirplanes;
    }

    public void close() {
        System.out.println("Closing Airplane Manager");
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

    public void displayMenu(Utility u) {
        System.out.println("--------------------------------");
        System.out.println("Airplane Management");
        System.out.println("1. Add an airplane");
        System.out.println("2. Delete an airplane");
        System.out.println("3. Edit an airplane");
        System.out.println("4. Display an airplane");
        System.out.println("5. Cancel");
        int option = u.getIntegerInput();
        System.out.println("--------------------------------");
        System.out.println();

        switch (option) {
            case 1:
                String[] options = {"Enter the make of the airplane:", "Enter the model of the airplane:", "Enter the type of the airplane:", "Enter the fuel tank size of the airplane:", "Enter the fuel burn of the airplane:", "Enter the air speed of the airplane:"};
                String[] inputs = new String[6];
                System.out.println("Add an airplane");
                for (int i = 0; i < options.length; i++) {
                    System.out.println(options[i]);
                    if (i == 3 || i == 4 || i == 5) {
                        inputs[i] = Double.toString(u.getDoubleInput());
                    } else {
                        inputs[i] = u.getStringInput();
                    }
                }
                this.add(new Airplane(inputs[0], inputs[1], inputs[2], inputs[3], inputs[4], inputs[5]));
                break;
            case 2:
                this.displayAirplanes();
                Airplane[] airplanesToDelete = new Airplane[this.airplanes.size()];
                for (int i = 0; i < this.airplanes.size(); i++) {
                    System.out.println((i + 1) + ". " + this.airplanes.get(i).getMake() + " " + this.airplanes.get(i).getModel());
                    airplanesToDelete[i] = this.airplanes.get(i);
                }
                System.out.println("Select or search an airplane to delete: ");
                String deleteOption = u.getStringInput();
                if (u.isNumeric(deleteOption)) {
                    int deleteOptionNum = Integer.parseInt(deleteOption);
                    this.delete(airplanesToDelete[deleteOptionNum - 1]);
                } else {
                    System.out.println("Searching for airplanes with the following criteria: " + deleteOption);
                    Vector<Airplane> deleteSearchOptions = this.search(deleteOption);
                    System.out.println(deleteSearchOptions.size() + " airplanes found.");
                    if (deleteSearchOptions.size() == 1) {
                        this.delete(deleteSearchOptions.get(0));
                    } else {
                        for (int i = 0; i < deleteSearchOptions.size(); i++) {
                            System.out.println((i + 1) + ". " + deleteSearchOptions.get(i).getMake() + " " + deleteSearchOptions.get(i).getModel());
                        }
                        System.out.println("Select an airplane to delete from your search: ");
                        int deleteOptionNum = u.getIntegerInput();
                        this.delete(deleteSearchOptions.get(deleteOptionNum - 1));
                    }
                }
                
                this.displayAirplanes();
                break;
            case 3:
                System.out.println("Edit an airplane");
                break;
            case 4:
                System.out.println("Display an airplane");
                break;
            case 5:
                break;
            default:
                System.out.println("Please select a valid option.");
                break;
        }
    }
}
