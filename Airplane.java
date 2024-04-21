public class Airplane {
    private String make;
    private String model;
    private String type;
    private double fuelTankSize; // Liters
    private double fuelBurn; // Liters per hour
    private double airSpeed; // Knots

    public Airplane() {
        this.make = "Generic";
        this.model = "Generic";
        this.type = "Generic";
        this.fuelTankSize = 0;
        this.fuelBurn = 0;
        this.airSpeed = 0;
    }

    public Airplane(String make, String model, String type, String fuelTankSize, String fuelBurn, String airSpeed) {
        this.make = make;
        this.model = model;
        this.type = type;
        this.fuelTankSize = Double.parseDouble(fuelTankSize);
        this.fuelBurn = Double.parseDouble(fuelBurn);
        this.airSpeed = Double.parseDouble(airSpeed);
    }

    public String getMake() {
        return this.make;
    }

    public String getModel() {
        return this.model;
    }

    public String getType() {
        return this.type;
    }

    public double getFuelTankSize() {
        return this.fuelTankSize;
    }

    public double getFuelBurn() {
        return this.fuelBurn;
    }

    public double getAirSpeed() {
        return this.airSpeed;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setFuelTankSize(double fuelTankSize) {
        this.fuelTankSize = fuelTankSize;
    }

    public void setFuelBurn(double fuelBurn) {
        this.fuelBurn = fuelBurn;
    }

    public void setAirSpeed(double airSpeed) {
        this.airSpeed = airSpeed;
    }

    public double calcRange() {
        double nauticalMiles = this.getFuelTankSize() / this.getFuelBurn() * this.getAirSpeed();
        double range = nauticalMiles * 1.15078;
        return range;
    }
}
