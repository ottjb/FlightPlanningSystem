package Managers;

import java.util.Vector;

import Objects.Airplane;
import Objects.Airport;

public class FlightPlanner {
    AirportManager airportManager;
    AirplaneManager airplaneManager;

    public FlightPlanner(AirportManager airportManager, AirplaneManager airplaneManager) {
        this.airportManager = airportManager;
        this.airplaneManager = airplaneManager;
    }

    public Vector<Airport> planFlight(int departureAirportIndex, int arrivalAirportIndex, int airplaneIndex) {
        Vector<Airport> flightPlan = new Vector<Airport>();
        Airport departureAirport = airportManager.getAirport(departureAirportIndex);
        Airport arrivalAirport = airportManager.getAirport(arrivalAirportIndex);
        Airplane airplane = airplaneManager.getAirplane(airplaneIndex);
        return flightPlan;
    }
}
