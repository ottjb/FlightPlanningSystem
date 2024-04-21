import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JWindow;

public class FlightPlanningSystem {
    public static void main(String[] args) {
        AirplaneManager airplaneManager = new AirplaneManager();
        AirportManager airportManager = new AirportManager();

        FlightPlanningSystemGUIV2 gui = new FlightPlanningSystemGUIV2(airplaneManager, airportManager);
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
