public class FlightPlanningSystem {
    public static void main(String[] args) {
        AirplaneManager airplaneManager = new AirplaneManager();
        AirportManager airportManager = new AirportManager();

        Airplane airplane = new Airplane("Boeing", "747", "Commercial", 100000, 1000, 500);

        airplane.getMake();
    }
}
