import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Vector;

public class FlightPlanner {
    AirportManager airportManager;
    AirplaneManager airplaneManager;

    Graph graph;

    public FlightPlanner(AirportManager airportManager, AirplaneManager airplaneManager) {
        this.airportManager = airportManager;
        this.airplaneManager = airplaneManager;
    }

    public Vector<Airport> planFlight(int departureAirportIndex, int arrivalAirportIndex, int airplaneIndex) {
        Vector<Airport> flightPlan = new Vector<Airport>();
        Airport departureAirport = airportManager.getAirport(departureAirportIndex);
        Airport arrivalAirport = airportManager.getAirport(arrivalAirportIndex);
        Airplane airplane = airplaneManager.getAirplane(airplaneIndex);

        double airplaneRange = airplane.calcRange();
        double distanceBetweenAirports = departureAirport.calcDistance(arrivalAirport);

        System.out.println("Airplane range: " + airplaneRange);
        System.out.println("Distance between airports: " + distanceBetweenAirports);

        graph = new Graph();
        for(int i = 0; i < airportManager.getAirportCount(); i++) {
            graph.addAirport(airportManager.getAirport(i));
        }
        addEdges(graph, airplane.calcRange());

        flightPlan = astar(graph, departureAirport, arrivalAirport);

        return flightPlan;
    }

    private void addEdges(Graph graph, double range) {
        for(int i = 0; i < airportManager.getAirportCount(); i++) {
            Airport source = airportManager.getAirport(i);
            for(int j = 0; j < airportManager.getAirportCount(); j++) {
                Airport destination = airportManager.getAirport(j);
                double distance = source.calcDistance(destination);

                if (distance <= range) {
                    graph.addEdge(source, destination, distance);
                }
            }
        }
    }

    private Vector<Airport> astar(Graph graph, Airport start, Airport destination) {
        Map<Airport, Double> gScore = new HashMap<>();
        Map<Airport, Double> fScore = new HashMap<>();
        Map<Airport, Airport> cameFrom = new HashMap<>();

        for (int i = 0; i < airportManager.getAirportCount(); i++) {
            Airport airport = airportManager.getAirport(i);
            gScore.put(airport, Double.MAX_VALUE);
            fScore.put(airport, Double.MAX_VALUE);
        }

        gScore.put(start, 0.0);
        fScore.put(start, start.calcDistance(destination));

        PriorityQueue<Airport> openSet = new PriorityQueue<>(Comparator.comparingDouble(fScore::get));
        openSet.add(start);

        while (!openSet.isEmpty()) {
            Airport current = openSet.poll();

            if (current.equals(destination)) {
                return reconstructPath(cameFrom, current);
            }

            for (Edge edge : graph.getEdges(current)) {
                Airport neighbor = edge.destination;
                double tentativeGScore = gScore.get(current) + edge.distance;

                if (tentativeGScore < gScore.get(neighbor)) {
                    cameFrom.put(neighbor, current);
                    gScore.put(neighbor, tentativeGScore);
                    fScore.put(neighbor, gScore.get(neighbor) + neighbor.calcDistance(destination));
                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }
        return null;
    }

    private Vector<Airport> reconstructPath(Map<Airport, Airport> cameFrom, Airport current) {
        Vector<Airport> path = new Vector<Airport>();
        while (cameFrom.containsKey(current)) {
            System.out.println("here");
            path.add(current);
            current = cameFrom.get(current);
        }
        Collections.reverse(path);
        return path;
    }
}
