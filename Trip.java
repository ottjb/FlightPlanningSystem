
import java.util.Vector;

public class Trip {
    private Vector<Airport> destinationAirports;
    private Vector<Airport> flightPlan;
    private Airplane airplane;

    public Trip() {
        this.destinationAirports = new Vector<Airport>();
        this.flightPlan = new Vector<Airport>();
        this.airplane = new Airplane();
    }

    public Trip(Vector<Airport> destinationAirports, Airport startAirport, Airplane airplane) {
        this.destinationAirports = destinationAirports;
        this.flightPlan.add(startAirport);
        this.airplane = airplane;
    }

    public Vector<Airport> getDestinationAirports() {
        return this.destinationAirports;
    }

    public Vector<Airport> getFlightPlan() {
        return this.flightPlan;
    }

    public Airplane getAirplane() {
        return this.airplane;
    }

    public void setDestinationAirports(Vector<Airport> destinationAirports) {
        this.destinationAirports = destinationAirports;
    }

    public void setFlightPlan(Vector<Airport> flightPlan) {
        this.flightPlan = flightPlan;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public void findNextAirport(){

    }
}
