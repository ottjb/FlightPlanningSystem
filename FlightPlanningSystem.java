
public class FlightPlanningSystem {
    public static void main(String[] args) {
        AirplaneManager airplaneManager = new AirplaneManager();
        AirportManager airportManager = new AirportManager();

        System.out.println("Welcome to the Flight Planning System");
        System.out.println("Created by: Jadon Ott, Mary-Alice Woolington, Brecken Merrill, and Cheyenne Kirby");
        System.out.println("THIS SOFTWARE IS NOT TO BE USED FOR FLIGHT PLANNING OR NAVIGATIONAL PURPOSE");

        boolean running = true;
        Utility utility = new Utility();
        while (running) {
            System.out.println("Please select an option:");
            System.out.println("1. Flight Planner");
            System.out.println("2. Airplane Management");
            System.out.println("3. Airport Management");
            System.out.println("4. Exit");
            int option = utility.getIntegerInput();

            switch (option) {
                case 1:
                    System.out.println("Flight Planner");
                    break;
                case 2:
                    airplaneManager.displayMenu();
                    break;
                case 3:
                    airportManager.displayMenu();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Please select a valid option.");
                    break;
            }
        }
    }
}
