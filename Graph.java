import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    Map<Airport, List<Edge>> adjacenyList = new HashMap<>();

    public void addAirport(Airport airport) {
        adjacenyList.put(airport, new ArrayList<>());
    }

    public void addEdge(Airport source, Airport destination, double distance) {
        Edge edge = new Edge(source, destination, distance);
        adjacenyList.get(source).add(edge);
        adjacenyList.get(destination).add(new Edge(destination, source, distance));
    }

    public List<Edge> getEdges(Airport airport) {
        return adjacenyList.get(airport);
    }
}
