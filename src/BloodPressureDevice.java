public class BloodPressureDevice extends Device {
    public BloodPressureDevice() {
        super("blood pressure monitor");
    }

    public Observation getObservation(int time, Patient patient) {
        int sys = 105 + Simulation.randomInt(30);
        int dia = 65 + Simulation.randomInt(20);

        if (Simulation.chance(5)) {
            sys = 145 + Simulation.randomInt(45);
            dia = 90 + Simulation.randomInt(35);
        }

        return new BloodPressureObservation(time, patient, sys, dia);
    }
}