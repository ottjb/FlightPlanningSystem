import java.awt.*;
import javax.swing.*;
import java.util.Random;

public class FlightPlannerDemo extends JFrame {

    private static final int numOfAirports = 5;
    private static final int screenWidth = 512;
    private static final int screenHeight = 512;
    private static final Random r = new Random();

    private JPanel bestRoutePanel;
    private JPanel currentRoutePanel;

    public FlightPlannerDemo() {
        setTitle("Flight Planner Demo");
        setSize(screenWidth, screenHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        AirportPoints[] airportPointsList = new AirportPoints[numOfAirports];
        for (int i = 0; i < numOfAirports; i++) {
            airportPointsList[i] = new AirportPoints(r.nextInt(screenWidth - 50) + 25, r.nextInt(screenHeight / 2 - 50) + 25);
            airportPointsList[i].displayPoints();
        }

        bestRoutePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.WHITE);
                drawPoints(g, airportPointsList);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.drawLine(0, 200, screenWidth, 200);

            }
        };

        currentRoutePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.WHITE);
                drawPoints(g, airportPointsList);
            }
        };

        getContentPane().setLayout(new GridLayout(2, 1));

        bestRoutePanel.setBackground(Color.BLACK);
        currentRoutePanel.setBackground(Color.BLACK);

        getContentPane().add(bestRoutePanel);
        getContentPane().add(currentRoutePanel);

        setVisible(true);
    }

    private void drawPoints(Graphics g, AirportPoints[] airportPointsList) {
        for (int i = 0; i < numOfAirports; i++) {
            g.fillOval(airportPointsList[i].x, airportPointsList[i].y, 8, 8);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FlightPlannerDemo::new);
    }
}

class AirportPoints {
    int x;
    int y;

    public AirportPoints(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void displayPoints() {
        System.out.println("x=" + this.x + " y=" + this.y);
    }
}
