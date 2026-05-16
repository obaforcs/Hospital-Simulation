public class Hospital {
    private Patient[] patients;
    private int patientCount;

    public Hospital(int maxPatients) {
        patients = new Patient[maxPatients];
        patientCount = 0;
    }

    public void addPatient(Patient patient) {
        if (patient != null && patientCount < patients.length) {
            patients[patientCount] = patient;
            patientCount++;
        }
    }

    public void updateAlerts(int time) {
        for (int i = 0; i < patientCount; i++) {
            patients[i].generateAlerts(time);
        }
    }

    public Patient getPatient(int index) {
        if (index < 0 || index >= patientCount) {
            return null;
        }

        return patients[index];
    }

    public int getPatientCount() {
        return patientCount;
    }
}