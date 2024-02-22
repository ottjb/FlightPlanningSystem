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
    private JPanel linePanel;

    public FlightPlannerDemo() {
        setTitle("Flight Planner Demo");
        setSize(screenWidth, screenHeight);
        setLocation(512, 256);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        AirportPoints[] airportPointsList = new AirportPoints[numOfAirports];
        for (int i = 0; i < numOfAirports; i++) {
            airportPointsList[i] = new AirportPoints(r.nextInt(screenWidth - 50) + 25, r.nextInt(screenHeight / 2 - 50) + 25);
        }

        bestRoutePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.WHITE);
                drawPoints(g, airportPointsList);
            }
        };

        currentRoutePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.WHITE);
                drawPoints(g, airportPointsList);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.drawLine(0, 0, screenWidth, 0);
            }
        };

        bestRoutePanel.setLayout(new BorderLayout());
        currentRoutePanel.setLayout(new BorderLayout());
        getContentPane().setLayout(new GridLayout(2, 1));

        bestRoutePanel.setBackground(Color.BLACK);
        currentRoutePanel.setBackground(Color.BLACK);

        getContentPane().add(bestRoutePanel);
        getContentPane().add(currentRoutePanel);

        setVisible(true);
        
        
        bestRoutePanel.add(createPathPanel(airportPointsList));
        currentRoutePanel.add(createPathPanel(airportPointsList));

        repaint();
    }


    private void drawPoints(Graphics g, AirportPoints[] airportPointsList) {
        for (int i = 0; i < numOfAirports; i++) {
            if (i == 0) {
                g.setColor(Color.GREEN);
            } else if (i == numOfAirports - 1) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.WHITE);
            }
            g.fillOval(airportPointsList[i].x - 4, airportPointsList[i].y - 4, 8, 8);
        }
    }

    private static void drawLeg(Graphics g, AirportPoints start, AirportPoints end) {
        g.drawLine(start.x, start.y, end.x, end.y);
    }

    public static JPanel createLinePanel(AirportPoints[] airportPointsList, AirportPoints start, AirportPoints end) {
        JPanel panel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.WHITE);
                drawLeg(g, start, end);
            }
        };
        panel.setOpaque(false);
        return panel;
    }

    public static JPanel createPathPanel(AirportPoints[] airportPointsList) {
        JPanel panel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.WHITE);
                for(int i = 0; i < numOfAirports - 1; i++) {
                    drawLeg(g, airportPointsList[i], airportPointsList[i + 1]);
                }
            }
        };
        panel.setOpaque(false);
        return panel;
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
