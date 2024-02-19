import java.util.Vector;

public class Airport {
    private String ICAOIdentifier;
    private String name;
    private double latitude;
    private double longitude;
    private double COMFrequency;
    private String fuelType;
    
    // The `public Airport()` constructor is a default constructor that initializes the properties of
    // the `Airport` object to default values. In this case, the default values are:
    public Airport() {
        this.ICAOIdentifier = "Generic";
        this.name = "Generic";
        this.latitude = 0.0;
        this.longitude = 0.0;
        this.COMFrequency = 0.0;
        this.fuelType = "Generic";
    }

    // The `public Airport(String ICAOIdentifier, String name, double latitude, double longitude,
    // double COMFrequency, String fuelType)` is a constructor for the `Airport` class. It takes in
    // parameters representing the ICAO identifier, name, latitude, longitude, communication frequency,
    // and fuel type of an airport, and initializes the corresponding properties of the `Airport`
    // object with these values.
    public Airport(String ICAOIdentifier, String name, double latitude, double longitude, double COMFrequency, String fuelType) {
        this.ICAOIdentifier = ICAOIdentifier;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.COMFrequency = COMFrequency;
        this.fuelType = fuelType;
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
    public String getFuelType() {
        return this.fuelType;
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
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Vector<Airport> findNearbyAirports(Airplane airplane) {

        return new Vector<Airport>();
    }
}
