public class TemperatureObservation extends Observation {
    private double temperature;

    public TemperatureObservation(int time, Patient patient, double temperature) {
        super(time, patient);
        this.temperature = temperature;
    }

    public boolean dangerous() {
        return temperature >= 100.4 || temperature <= 95.0;
    }

    public Severity severity() {
        if (temperature >= 103.0 || temperature <= 94.0) {
            return Severity.URGENT;
        }

        return Severity.ROUTINE;
    }

    public String data() {
        return "temperature: " + temperature;
    }
}