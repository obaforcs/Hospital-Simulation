public class CallBellDevice extends Device {
    public CallBellDevice() {
        super("call bell");
    }

    public Observation getObservation(int time, Patient patient) {
        boolean urgent = false;
        String reason = "needs assistance";

        if (Simulation.chance(20)) {
            urgent = true;
            reason = "severe discomfort";
        }

        return new CallBellObservation(time, patient, reason, urgent);
    }
}