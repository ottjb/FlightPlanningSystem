import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JWindow;

import GUI.FlightPlanningSystemGUIV2;
import Managers.AirplaneManager;
import Managers.AirportManager;
import Managers.FlightPlanner;

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
