public class HeartRateObservation extends Observation {
    private int heartRate;

    public HeartRateObservation(int time, Patient patient, int heartRate) {
        super(time, patient);
        this.heartRate = heartRate;
    }

    public boolean dangerous() {
        return heartRate < 50 || heartRate > 120;
    }

    public Severity severity() {
        if (heartRate < 40 || heartRate > 140) {
            return Severity.URGENT;
        }

        return Severity.ROUTINE;
    }

    public String data() {
        return "heart rate: " + heartRate;
    }
}