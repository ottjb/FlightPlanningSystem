

import java.util.Vector;

public class Airport {
    private String ICAOIdentifier;
    private String name;
    private double latitude;
    private double longitude;
    private double COMFrequency;
    private String[] fuelTypes = new String[2];

    // The `public Airport()` constructor is a default constructor that initializes
    // the properties of
    // the `Airport` object to default values. In this case, the default values are:
    public Airport() {
        this.ICAOIdentifier = "Generic";
        this.name = "Generic";
        this.latitude = 0.0;
        this.longitude = 0.0;
        this.COMFrequency = 0.0;
        this.fuelTypes[0] = "Generic";
        this.fuelTypes[1] = "Generic";
    }

    // The `public Airport(String ICAOIdentifier, String name, double latitude,
    // double longitude,
    // double COMFrequency, String fuelType)` is a constructor for the `Airport`
    // class. It takes in
    // parameters representing the ICAO identifier, name, latitude, longitude,
    // communication frequency,
    // and fuel type of an airport, and initializes the corresponding properties of
    // the `Airport`
    // object with these values.
    public Airport(String ICAOIdentifier, String name, double latitude, double longitude, double COMFrequency,
            String[] fuelTypes) {
        this.ICAOIdentifier = ICAOIdentifier;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.COMFrequency = COMFrequency;
        this.fuelTypes = fuelTypes;
    }

    /**
     * @return The current airport's ICAO identifier.
     */
    public String getICAOIdentifier() {
        return this.ICAOIdentifier;
    }

    /**
     * @return The current airport's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return The current airport's latitude.
     */
    public double getLatitude() {
        return this.latitude;
    }

    /**
     * @return The current airport's longitude.
     */
    public double getLongitude() {
        return this.longitude;
    }

    /**
     * @return The current airport's communication frequency.
     */
    public double getCOMFrequency() {
        return this.COMFrequency;
    }

    /**
     * @return The current airport's fuel type.
     */
    public String[] getFuelType() {
        return this.fuelTypes;
    }

    /**
     * @param ICAOIdentifier The current airport's ICAO identifier.
     */
    public void setICAOIdentifier(String ICAOIdentifier) {
        this.ICAOIdentifier = ICAOIdentifier;
    }

    /**
     * @param name The current airport's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param latitude The current airport's latitude.
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @param longitude The current airport's longitude.
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * @param COMFrequency The current airport's communication frequency.
     */
    public void setCOMFrequency(double COMFrequency) {
        this.COMFrequency = COMFrequency;
    }

    /**
     * @param fuelType The current airport's fuel type.
     */
    public void setFuelType(String[] fuelTypes) {
        this.fuelTypes = fuelTypes;
    }

    public Vector<Airport> findNearbyAirports(Airplane airplane) {

        return new Vector<Airport>();
    }

    /**
     * @param airport The airport to calculate the distance to.
     * @return The distance between the current airport and the given airport in miles.
     */
    public double calcDistance(Airport airport) {
        double startAPLatitude = this.getLatitude();
        double startAPLongitude = this.getLongitude();
        double endAPLatitude = airport.getLatitude();
        double endAPLongitude = airport.getLongitude();

        double earthRadius = 6371;
        
        // Convert latitude and longitude from degrees to radians
        double startAPlatRad = Math.toRadians(startAPLatitude);
        double startAPlongRad = Math.toRadians(startAPLongitude);
        double endAPlatRad = Math.toRadians(endAPLatitude);
        double endAPlongRad = Math.toRadians(endAPLongitude);

        // Calculate the differences between coordinates
        double latDiff = endAPlatRad - startAPlatRad;
        double lonDiff = endAPlongRad - startAPlongRad;

        // Calculate the distance using Haversine formula
        double a = Math.pow(Math.sin(latDiff / 2), 2) + Math.cos(startAPlatRad) * Math.cos(endAPlatRad) * Math.pow(Math.sin(lonDiff / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = earthRadius * c;

        // Convert distance from kilometers to miles
        double distanceMiles = distance * 0.621371;

        return distanceMiles;
    }

    public double calcHeading(Airport airport) {
        double startAPLatitude = this.getLatitude();
        double startAPLongitude = this.getLongitude();
        double endAPLatitude = airport.getLatitude();
        double endAPLongitude = airport.getLongitude();

        // Convert to radians
        double startAPlatRad = Math.toRadians(startAPLatitude);
        double startAPlongRad = Math.toRadians(startAPLongitude);
        double endAPlatRad = Math.toRadians(endAPLatitude);
        double endAPlongRad = Math.toRadians(endAPLongitude);

        double deltaLong = endAPlongRad - startAPlongRad;

        double y = Math.sin(deltaLong) * Math.cos(endAPlatRad);
        double x = Math.cos(startAPlatRad) * Math.sin(endAPlatRad) - Math.sin(startAPlatRad) * Math.cos(endAPlatRad) * Math.cos(deltaLong);

        double heading = Math.atan2(y, x);
        heading = Math.toDegrees(heading);

        heading = (heading + 360) % 360;

        heading = Math.round(heading * 1000.0) / 1000.0;

        heading = 360 - heading;

        return heading;
    }
}
