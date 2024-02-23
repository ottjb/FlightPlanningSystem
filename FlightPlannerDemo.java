import java.awt.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
import java.util.Comparator;

public class FlightPlannerDemo extends JFrame {

    private static final int numOfAirports = 20;
    private static final int screenWidth = 512;
    private static final int screenHeight = 512;
    private static final Random r = new Random();
    private static int generation = 0;
    private static ArrayList<int[]> population = new ArrayList<int[]>();

    private JPanel bestRoutePanel;
    private static JPanel currentRoutePanel;
    private JPanel linePanel;

    public FlightPlannerDemo() {
        setTitle("Flight Planner Demo");
        setSize(screenWidth, screenHeight);
        setLocation(512, 128);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        AirportPoints[] airportPointsList = new AirportPoints[numOfAirports];
        for (int i = 0; i < numOfAirports; i++) {
            airportPointsList[i] = new AirportPoints(r.nextInt(screenWidth - 50) + 25,
                    r.nextInt(screenHeight / 2 - 50) + 25);
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

        Path testPath = new Path();

        for (int i = 0; i < numOfAirports; i++) {
            testPath.addPath(airportPointsList[i]);
        }

        Path testPath2 = new Path();

        testPath2.generatePath(numOfAirports, airportPointsList);

        System.out.println("Distance: " + testPath.calculateDistance());
        System.out.println("Fitness: " + testPath.calculateFitness());

        bestRoutePanel.add(createPathPanel(testPath.path));

        generation++;
        Generation gen = new Generation(generation);
        gen.generateRandomPopulation(numOfAirports, airportPointsList, 1000);
        currentRoutePanel.add(createPathPanel(gen.highestFitnessPath.path));
        gen.calcAverageFitness();

        ArrayList<Path> topTenPercent = gen.getTopTenPercent();
        for (int i = 0; i < topTenPercent.size(); i++) {
            System.out.println((i + 1) + " Fitness: " + topTenPercent.get(i).calculateFitness());
        }

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

    public static JPanel createPathPanel(ArrayList<AirportPoints> path) {
        JPanel panel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.WHITE);
                for (int i = 0; i < path.size() - 1; i++) {
                    drawLeg(g, path.get(i), path.get(i + 1));
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

class Path {
    ArrayList<AirportPoints> path = new ArrayList<>();
    double distance;
    double fitness;

    public Path(AirportPoints path) {
        this.path.add(path);
        this.distance = 0;
        this.fitness = 0;
    }

    public Path() {
        this.path = new ArrayList<>();
        this.distance = 0;
        this.fitness = 0;
    }

    public void addPath(AirportPoints path) {
        this.path.add(path);
    }

    public double calculateDistance() {
        int totalDist = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            int x1 = path.get(i).x;
            int y1 = path.get(i).y;
            int x2 = path.get(i + 1).x;
            int y2 = path.get(i + 1).y;
            totalDist += Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
            // System.out.println("Total Distance: " + totalDist);
        }
        distance = totalDist;
        return distance;
    }

    public double calculateFitness() {
        fitness = (100 / this.calculateDistance()) / path.size();
        return fitness;
    }

    public void generatePath(int numOfAirports, AirportPoints[] airportPointsList) {
        Random r = new Random();
        ArrayList<AirportPoints> notVisited = new ArrayList<>(airportPointsList.length);
        for (int i = 0; i < airportPointsList.length; i++) {
            notVisited.add(airportPointsList[i]);
        }
        this.path.add(notVisited.get(0));
        notVisited.remove(0);
        while (this.path.get(this.path.size() - 1) != airportPointsList[numOfAirports - 1] && notVisited.size() > 0) {
            int nextAirport = r.nextInt(notVisited.size());
            this.path.add(notVisited.get(nextAirport));
            notVisited.remove(nextAirport);
        }
    }
}

class Generation {
    ArrayList<Path> population = new ArrayList<Path>();
    int generation;
    Path highestFitnessPath;

    public Generation(int generation) {
        this.generation = generation;
    }

    public void addPath(Path path) {
        this.population.add(path);
    }

    public void generateRandomPopulation(int numOfAirports, AirportPoints[] airportPointsList, int size) {
        for (int i = 0; i < size; i++) {
            Path p = new Path();
            p.generatePath(numOfAirports, airportPointsList);
            p.calculateFitness();
            population.add(p);
            double highestFitness = 0;
            if (p.calculateFitness() > highestFitness) {
                highestFitness = p.calculateFitness();
                highestFitnessPath = p;
            }
        }
    }

    public void calcAverageFitness() {
        double totalFitness = 0;
        for (int i = 0; i < population.size(); i++) {
            totalFitness += population.get(i).calculateFitness();
        }
        System.out.println("Average Fitness: " + totalFitness / population.size());
    }

    public ArrayList<Path> getTopTenPercent() {
        ArrayList<Path> topTenPercent = new ArrayList<Path>();
        Collections.sort(population, Comparator.comparingDouble(Path::calculateFitness).reversed());
        for (int i = 0; i < population.size() / 10; i++) {
            topTenPercent.add(population.get(i));
        }
        return topTenPercent;
    }
}
