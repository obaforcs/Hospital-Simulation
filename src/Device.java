public abstract class Device {
    private String name;

    public Device(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract Observation getObservation(int time, Patient patient);
}