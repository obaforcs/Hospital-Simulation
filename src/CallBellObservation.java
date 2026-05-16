public class CallBellObservation extends Observation {
    private String reason;
    private boolean urgent;

    public CallBellObservation(int time, Patient patient, String reason, boolean urgent) {
        super(time, patient);
        this.reason = reason;
        this.urgent = urgent;
    }

    public boolean dangerous() {
        return true;
    }

    public Severity severity() {
        if (urgent) {
            return Severity.URGENT;
        }

        return Severity.ROUTINE;
    }

    public String data() {
        return "call bell: " + reason;
    }
}