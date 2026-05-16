public class Alert {
    private Observation observation;
    private int raisedTime;
    private int resolvedTime;
    private Severity severity;

    public Alert(Observation observation, int raisedTime, Severity severity) {
        this.observation = observation;
        this.raisedTime = raisedTime;
        this.severity = severity;
        resolvedTime = -1;
    }

    public Observation getObservation() {
        return observation;
    }

    public int getRaisedTime() {
        return raisedTime;
    }

    public int getResolvedTime() {
        return resolvedTime;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void resolve(int time) {
        resolvedTime = time;
    }

    public String toString() {
        return "Alert[" + severity + "] patient=" + observation.getPatient().getId()
                + ", time=" + raisedTime
                + ", data=" + observation.data();
    }

    public boolean isResolved() {
        return resolvedTime >= 0;
    }

    public int timeToResolve() {
        if (!isResolved()) {
            return -1;
        }

        return resolvedTime - raisedTime;
    }
}