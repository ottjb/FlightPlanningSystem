

public class Airplane {
    private String make;
    private String model;
    private String type;
    private double fuelTankSize; // Liters
    private double fuelBurn; // Liters per hour
    private double airSpeed; // Knots

    // The `public Airplane()` constructor is a default constructor that initializes
    // the instance
    // variables of the `Airplane` class to default values. In this case, it sets
    // the `make`, `model`,
    // and `type` variables to "Generic", the `fuelTankSize` and `fuelBurn`
    // variables to 0, and the
    // `airSpeed` variable to 0. This constructor is called when an object of the
    // `Airplane` class is
    // created without any arguments.
    public Airplane() {
        this.make = "Generic";
        this.model = "Generic";
        this.type = "Generic";
        this.fuelTankSize = 0;
        this.fuelBurn = 0;
        this.airSpeed = 0;
    }

    // The `public Airplane(String make, String model, String type, double
    // fuelTankSize, double fuelBurn, double
    // airSpeed)` constructor is a parameterized constructor that initializes the
    // instance variables of
    // the `Airplane` class with the values passed as arguments.
    public Airplane(String make, String model, String type, String fuelTankSize, String fuelBurn, String airSpeed) {
        this.make = make;
        this.model = model;
        this.type = type;
        this.fuelTankSize = Double.parseDouble(fuelTankSize);
        this.fuelBurn = Double.parseDouble(fuelBurn);
        this.airSpeed = Double.parseDouble(airSpeed);
    }

    /**
     * @return The make of the airplane.
     */
    public String getMake() {
        return this.make;
    }

    /**
     * @return The model of the airplane.
     */
    public String getModel() {
        return this.model;
    }

    /**
     * @return The type of the airplane.
     */
    public String getType() {
        return this.type;
    }

    /**
     * @return The fuel tank size of the airplane.
     */
    public double getFuelTankSize() {
        return this.fuelTankSize;
    }

    /**
     * @return The fuel burn of the airplane.
     */
    public double getFuelBurn() {
        return this.fuelBurn;
    }

    /**
     * @return The air speed of the airplane.
     */
    public double getAirSpeed() {
        return this.airSpeed;
    }

    /**
     * @param make The "make" parameter is a String that represents the make of an
     *             airplane.
     */
    public void setMake(String make) {
        this.make = make;
    }

    /**
     * @param model The "model" parameter is a String that represents the model of
     *              an airplane.
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @param type The "type" parameter is a String that represents the type of an
     *             airplane.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @param fuelTankSize The "fuelTankSize" parameter is a double that represents
     *                     the fuel tank size of an airplane.
     */
    public void setFuelTankSize(double fuelTankSize) {
        this.fuelTankSize = fuelTankSize;
    }

    /**
     * @param fuelBurn The "fuelBurn" parameter is a double that represents the fuel
     *                 burn of an airplane.
     */
    public void setFuelBurn(double fuelBurn) {
        this.fuelBurn = fuelBurn;
    }

    /**
     * @param airSpeed The "airSpeed" parameter is a double that represents the air
     *                 speed of an airplane.
     */
    public void setAirSpeed(double airSpeed) {
        this.airSpeed = airSpeed;
    }

    /**
     * @return The range of the airplane in miles.
     */
    public double calcRange() {
        double nauticalMiles = this.getFuelTankSize() / this.getFuelBurn() * this.getAirSpeed();
        double range = nauticalMiles * 1.15078;
        return range;
    }

}