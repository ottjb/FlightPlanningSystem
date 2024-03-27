import javax.swing.JFrame;
import javax.swing.JWindow;

import GUI.FlightPlanningSystemGUI;

public class FlightPlanningSystem {
    public static void main(String[] args) {
        AirplaneManager airplaneManager = new AirplaneManager();
        AirportManager airportManager = new AirportManager();
        // JFrame gui = new JFrame();

        // gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // gui.setSize(600, 600);
        // gui.setTitle("Flight Planning System");
        // gui.setVisible(true);

        boolean running = true;
        int option;
        Utility utility = new Utility();
        while (running) {
        System.out.println("--------------------------------");
        System.out.println("Please select an option:");
        System.out.println("1. Flight Planner");
        System.out.println("2. Airplane Management");
        System.out.println("3. Airport Management");
        System.out.println("4. Exit");
        option = utility.getIntegerInput();
        System.out.println("--------------------------------");
        System.out.println();

        switch (option) {
        case 1:
        System.out.println("Flight Planner");
        break;
        case 2:
        airplaneManager.displayMenu(utility);
        break;
        case 3:
        airportManager.displayMenu(utility);
        break;
        case 4:
        running = false;
        utility.close();
        break;
        default:
        System.out.println("Please select a valid option.");
        break;
        }
        }
    }

}
