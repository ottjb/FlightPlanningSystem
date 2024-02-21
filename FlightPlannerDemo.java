import java.awt.*;
import javax.swing.*;

public class FlightPlannerDemo {
    public static void main(String[] args) {
        int screenWidth = 300;
        int screenHeight = 300;

        DrawScreen frame = new DrawScreen();
        frame.setSize(screenWidth, screenHeight);
        frame.setLocation(512, 256);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Flight Planner Demo");
        frame.setVisible(true);
    }
}

class DrawScreen extends JFrame {
    public DrawScreen() {
        setLayout(new GridLayout(2, 0));
        JPanel bestRouteDisplay = new JPanel();
        JPanel currentRouteDisplay = new JPanel();

        bestRouteDisplay.setBackground(Color.BLACK);
        currentRouteDisplay.setBackground(Color.BLUE);

        add(bestRouteDisplay);
        add(currentRouteDisplay);
    }
}