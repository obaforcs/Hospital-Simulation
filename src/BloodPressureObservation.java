public class BloodPressureObservation extends Observation {
    private int systolic;
    private int diastolic;

    public BloodPressureObservation(int time, Patient patient, int systolic, int diastolic) {
        super(time, patient);
        this.systolic = systolic;
        this.diastolic = diastolic;
    }

    public boolean dangerous() {
        return systolic > 140 || systolic < 90 || diastolic > 90 || diastolic < 60;
    }

    public Severity severity() {
        if (systolic > 180 || systolic < 80 || diastolic > 120 || diastolic < 50) {
            return Severity.URGENT;
        }

        return Severity.ROUTINE;
    }

    public String data() {
        return "blood pressure: " + systolic + "/" + diastolic;
    }
}