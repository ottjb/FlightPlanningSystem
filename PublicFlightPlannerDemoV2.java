import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

import javax.swing.*;
import java.awt.*;

public class PublicFlightPlannerDemoV2 extends JPanel{

    public static void main(String[] args) {
        
        Random r = new Random();

        // Genetic Algorithm Variables
        int populationSize = 80;
        int currentGeneration = 0;
        int generations = 100;
        double mutationRate = 5;
        double crossoverRate = 0.7;
        int airplaneRange = 500;

        // Plot Variables
        int plotCount = 10;
        int screenWidth = 1000;
        int screenHeight = 1000;

        // Create Plots
        ArrayList<Plot> plots = new ArrayList<Plot>();
        for (int i = 0; i < plotCount; i++) {
            Plot p = new Plot(screenWidth, screenHeight, r);
            plots.add(p);
        }
        //plots.forEach(p -> System.out.println("x: " + p.x + " y: " + p.y));

        // Create 
        currentGeneration++;
        ArrayList<Route> population = new ArrayList<Route>();
        for (int i = 0; i < populationSize; i++) {
            Route r1 = new Route(plots, r, airplaneRange);
            population.add(r1);
        }
        Collections.sort(population, (a, b) -> Double.compare(b.fitness, a.fitness));
        //population.forEach(p -> System.out.println("Fitness: " + p.fitness + " Length: " + p.length));
        // ArrayList<Plot> bestRoute = new ArrayList<Plot>(population.get(0).legs);
        // bestRoute.forEach(p -> System.out.println("x: " + p.x + " y: " + p.y));

        // Genetic Algorithm
        double averageFitness = 0;
        for (int j = currentGeneration; j <= generations; j++) {
            // Calculate the average fitness of the last generation
            double lastAverageFitness = averageFitness;
            averageFitness = 0;
            for (Route r1 : population) {
                averageFitness += r1.fitness;
            }
            averageFitness /= populationSize;
            if (averageFitness == lastAverageFitness) {
                System.out.println("Average fitness has not changed");
                System.out.println("Final Path:");
                population.get(0).printRoute();
                break;
            }
            System.out.println("Generation: " + j + " Average Fitness: " + averageFitness + " Length: " + population.get(0).length);
            ArrayList<Route> newPopulation = new ArrayList<Route>();
            for (int i = 0; i < populationSize / 2; i++) {
                newPopulation.add(population.get(i));
                if (r.nextInt(100) < mutationRate) {
                    Route child = population.get(i).mutate(r);
                    newPopulation.add(child);
                } else {
                    newPopulation.add(population.get(i));
                }
            }
            Collections.sort(newPopulation, (a, b) -> Double.compare(b.fitness, a.fitness));
            population = new ArrayList<Route>(newPopulation);
        }
        //population.forEach(p -> System.out.println("Fitness: " + p.fitness + " Length: " + p.length));
    }
}

class Plot {
    double x;
    double y;

    public Plot(int screenWidth, int screenHeight, Random r) {
        generatePlot(screenWidth, screenHeight, r);
    }

    private void generatePlot(int screenWidth, int screenHeight, Random r) {
        x = r.nextInt(screenWidth - 50) + 25;
        y = r.nextInt(screenHeight - 50) + 25;
    }
}

class Route {
    double fitness;
    double length;
    double longestLeg = 0;
    ArrayList<Plot> legs;

    public Route(ArrayList<Plot> plots, Random r, int airplaneRange) {
        generateRoute(plots, r, airplaneRange);
        calculateDistance();
        calculateFitness();
    }

    private void generateRoute(ArrayList<Plot> plots, Random r, int airplaneRange) {
        legs = new ArrayList<Plot>();
        Plot startPlot = plots.get(0);
        Plot endPlot = plots.get(plots.size() - 1);

        legs.add(startPlot);

        while (legs.get(legs.size() - 1) != endPlot) {
            ArrayList<Plot> availablePlots = new ArrayList<Plot>(plots);
            availablePlots.removeAll(legs);

            Collections.shuffle(availablePlots);

            Plot nextPlot = null;
            for (Plot p : availablePlots) {
                double distance = calculateDistance(legs.get(legs.size() - 1), p);
                if (distance <= airplaneRange) {
                    nextPlot = p;
                    break;
                }
            }

            if (nextPlot != null) {
                legs.add(nextPlot);
            } else {
                //System.out.println("No available plots left in range");
                break;
            }
        }
    }

    public void calculateDistance() {
        double totalDistance = 0;
        for (int i = 0; i < legs.size() - 1; i++) {
            double x = Math.pow(legs.get(i).x - legs.get(i + 1).x, 2);
            double y = Math.pow(legs.get(i).y - legs.get(i + 1).y, 2);
            double distance = Math.sqrt(x + y);
            totalDistance += distance;
            length = totalDistance;
        }
    }

    private double calculateDistance(Plot p1, Plot p2) {
        double x = Math.pow(p1.x - p2.x, 2);
        double y = Math.pow(p1.y - p2.y, 2);
        return Math.sqrt(x + y);
    }

    public void calculateFitness() {
            fitness = 1 / (length * (legs.size() + 1));
    }

    public void printRoute() {
        legs.forEach(p -> System.out.println("x: " + p.x + " y: " + p.y));
    }

    public Route mutate(Random r) {
        Route child = this;
        try {
            int index1 = r.nextInt(child.legs.size() - 2) + 1;
            int index2 = r.nextInt(child.legs.size() - 2) + 1;

            Plot temp = child.legs.get(index1);
            child.legs.set(index1, child.legs.get(index2));
            child.legs.set(index2, temp);
        } catch (IllegalArgumentException e) {
            //System.out.println("Index out of bounds");
            return child;
        }
        return child;
    }
}
