public abstract class Observation {
    private int time;
    private Patient patient;

    public Observation(int time, Patient patient) {
        this.time = time;
        this.patient = patient;
    }

    public int getTime() {
        return time;
    }

    public Patient getPatient() {
        return patient;
    }

    public abstract boolean dangerous();

    public abstract Severity severity();

    public abstract String data();
}