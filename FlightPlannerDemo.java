import java.awt.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
import java.util.Comparator;

public class FlightPlannerDemo extends JFrame {

    private static final int numOfAirports = 30;
    private static final int screenWidth = 512;
    private static final int screenHeight = 512;
    private static final Random r = new Random();

    private JPanel bestRoutePanel;
    private static JPanel currentRoutePanel;

    public FlightPlannerDemo() {
        setTitle("Flight Planner Demo");
        setSize(screenWidth, screenHeight);
        setLocation(512, 128);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        AirportPoints[] airportPointsList = new AirportPoints[numOfAirports];
        for (int i = 0; i < numOfAirports; i++) {
            airportPointsList[i] = new AirportPoints(r.nextInt(screenWidth - 50) + 25,
                    r.nextInt(screenHeight - 75) + 25);
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
                //g2.drawLine(0, 0, screenWidth, 0);
            }
        };

        bestRoutePanel.setLayout(new BorderLayout());
        currentRoutePanel.setLayout(new BorderLayout());
        getContentPane().setLayout(new GridLayout(1, 1));

        bestRoutePanel.setBackground(Color.BLACK);
        currentRoutePanel.setBackground(Color.BLACK);

        //getContentPane().add(bestRoutePanel);
        getContentPane().add(currentRoutePanel);

        setVisible(true);

        Path testPath = new Path();

        for (int i = 0; i < numOfAirports; i++) {
            testPath.addPath(airportPointsList[i]);
        }

        Path testPath2 = new Path();

        testPath2.generatePath(numOfAirports, airportPointsList);

        bestRoutePanel.add(createPathPanel(testPath.path));

        Generation gen = new Generation();
        gen.generateRandomPopulation(numOfAirports, airportPointsList, 100);
        for (int i = 0; i < 10; i++) {
            gen.generateNextGeneration();
            // if(gen.highestFitnessPath.fitness == 0) {
            //     System.out.println("No possible route found");
            //     break;
            // }
        }
        currentRoutePanel.add(createPathPanel(gen.highestFitnessPath.path));

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
        fitness = 1 / (1 + this.calculateDistance());
        //System.out.println("Fitness: " + fitness);
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

    public Generation() {
        this.generation = 0;
    }

    public void addPath(Path path) {
        this.population.add(path);
    }

    public void generateRandomPopulation(int numOfAirports, AirportPoints[] airportPointsList, int size) {
        generation++;
        System.out.println("---Generation: " + generation + "---");
        for (int i = 0; i < size; i++) {
            Path p = new Path();
            p.generatePath(numOfAirports, airportPointsList);
            p.calculateFitness();
            population.add(p);
            // double highestFitness = -999;
            // if (p.fitness > highestFitness) {
            //     //System.out.println("here");
            //     highestFitness = p.fitness;
            //     highestFitnessPath = p;
            // }
        }
        sortPopulation();
        highestFitnessPath = population.get(population.size() - 1);
        System.out.println("Highest Fitness: " + highestFitnessPath.fitness);
        calcAverageFitness();
    }

    public void generateNextGeneration() {
        Random r = new Random();
        generation++;
        System.out.println("---Generation: " + generation + "---");
        ArrayList<Path> newPopulation = new ArrayList<Path>();
        ArrayList<Path> topTenPercent = getTopTenPercent();
        for (int i = 0; i < population.size(); i++) {
            Path parent1 = topTenPercent.get(r.nextInt(topTenPercent.size()));
            Path parent2 = topTenPercent.get(r.nextInt(topTenPercent.size()));
            if (r.nextInt(2) == 0) {
                Path child = crossover(parent1, parent2);
                newPopulation.add(child);
            } else {
                if (r.nextInt(2) == 0) {
                    Path child = mutate(parent1);
                    newPopulation.add(child);
                } else {
                    Path child = mutate(parent2);
                    newPopulation.add(child);
                }
            }
            //Path child = crossover(parent1, parent2);
            // double highestFitness = 0;
            // if (child.calculateFitness() > highestFitness) {
            //     highestFitness = child.calculateFitness();
            //     highestFitnessPath = child;
            // }
        }
        population = newPopulation;
        sortPopulation();
        highestFitnessPath = population.get(population.size() - 1);
        System.out.println("Highest Fitness: " + highestFitnessPath.fitness);
        calcAverageFitness();
    }

    public Path mutate(Path path) {
        Random r = new Random();
        int i = r.nextInt(path.path.size());
        if (i == 0 || i == path.path.size() - 1) {
            return path;
        }
        int j = r.nextInt(path.path.size());
        if(j == 0 || j == path.path.size() - 1) {
            return path;
        }
        AirportPoints temp = path.path.get(i);
        path.path.set(i, path.path.get(j));
        path.path.set(j, temp);
        return path;
    }

    public Path crossover(Path parent1, Path parent2) {
        Path child = new Path();
        Random r = new Random();
        int i = r.nextInt(2);
        int childSize;
        if(i == 0) {
            childSize = parent1.path.size();
        } else {
            childSize = parent2.path.size();
        }
        child.path.add(parent1.path.get(0));
        for (int j = 1; i < childSize; i++) {
            r.nextInt(2);
            if (i == 0) {
                if (!child.path.contains(parent1.path.get(j))) {
                    child.path.add(parent1.path.get(j));
                } else {
                    child.path.add(parent2.path.get(j));
                }
            } else {
                if (!child.path.contains(parent2.path.get(j))) {
                    child.path.add(parent2.path.get(j));
                } else {
                    child.path.add(parent1.path.get(j));
                }
            }
        }
        child.path.add(parent1.path.get(parent1.path.size() - 1));
        return child;
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
        for (int i = population.size() - 1; i > population.size() * 0.9; i--) {
            topTenPercent.add(population.get(i));
        }
        return topTenPercent;
    }

    public void sortPopulation() {
        Collections.sort(population, Comparator.comparingDouble(Path::calculateFitness));
    }
}
