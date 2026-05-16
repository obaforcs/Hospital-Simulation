public class TemperatureDevice extends Device {
    public TemperatureDevice() {
        super("temperature monitor");
    }

    public Observation getObservation(int time, Patient patient) {
        double value = 97.5 + Simulation.randomDouble() * 4.0;

        if (Simulation.chance(4)) {
            value = 101.0 + Simulation.randomDouble() * 4.5;
        }

        return new TemperatureObservation(time, patient, value);
    }
}