package simulator;

public class SensorEvent {
    private String vin;
    private double temperature;
    private double speed;
    private int rpm;
    private long timestamp;

    public SensorEvent(String vin, double temperature, double speed, int rpm, long timestamp) {
        this.vin = vin;
        this.temperature = temperature;
        this.speed = speed;
        this.rpm = rpm;
        this.timestamp = timestamp;
    }

    // Getters and Setters
    public String getVin() { return vin; }
    public void setVin(String vin) { this.vin = vin; }

    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }

    public double getSpeed() { return speed; }
    public void setSpeed(double speed) { this.speed = speed; }

    public int getRpm() { return rpm; }
    public void setRpm(int rpm) { this.rpm = rpm; }

    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
}
