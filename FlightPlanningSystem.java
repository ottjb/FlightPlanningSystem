import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JWindow;

/*
 * @todo: When adding/editing/deleting an airplane or airplane, the Flight Planner should be updated.
 * @todo: Input validation for adding/editing an airplane or airport.
 * @todo: align sidebar text (maybe html it idk)
 */

public class FlightPlanningSystem {
    public static void main(String[] args) {
        AirplaneManager airplaneManager = new AirplaneManager();
        AirportManager airportManager = new AirportManager();
        FlightPlanner flightPlanner = new FlightPlanner(airportManager, airplaneManager);

        FlightPlanningSystemGUIV2 gui = new FlightPlanningSystemGUIV2(airplaneManager, airportManager, flightPlanner);
        centerWindow(gui);
        gui.setVisible(true);
    }

    public static void centerWindow(Component window) {
        if (window instanceof JFrame) {
            JFrame frame = (JFrame) window;
            frame.setLocationRelativeTo(null);
        } else if (window instanceof JWindow) {
            JWindow frame = (JWindow) window;
            frame.setLocationRelativeTo(null);
        }
    }
}
