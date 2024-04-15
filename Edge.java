public class Edge {
    Airport source, destination;
    double distance;

    public Edge(Airport source, Airport destination, double distance) {
        this.source = source;
        this.destination = destination;
        this.distance = distance;
    }
}
