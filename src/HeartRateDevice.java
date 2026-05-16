public class HeartRateDevice extends Device {
    public HeartRateDevice() {
        super("heart rate monitor");
    }

    public Observation getObservation(int time, Patient patient) {
        int value = 60 + Simulation.randomInt(45);

        if (Simulation.chance(5)) {
            value = 125 + Simulation.randomInt(35);
        }

        return new HeartRateObservation(time, patient, value);
    }
}