public class Hospital {
    private Patient[] patients;
    private int patientCount;

    private Nurse[] nurses;
    private int nurseCount;

    private AlertQueue urgentAlerts;
    private AlertQueue routineAlerts;
    private AlertQueue completedAlerts;

    public Hospital(int maxPatients, int maxNurses) {
        patients = new Patient[maxPatients];
        patientCount = 0;

        nurses = new Nurse[maxNurses];
        nurseCount = 0;

        urgentAlerts = new AlertQueue(1000);
        routineAlerts = new AlertQueue(1000);
        completedAlerts = new AlertQueue(2000);
    }

    public AlertQueue getCompletedAlerts() {
        return completedAlerts;
    }

    public void addPatient(Patient patient) {
        if (patient != null && patientCount < patients.length) {
            patients[patientCount] = patient;
            patientCount++;
        }
    }

    public void addNurse(Nurse nurse) {
        if (nurse != null && nurseCount < nurses.length) {
            nurses[nurseCount] = nurse;
            nurseCount++;
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

    public int getNurseCount() {
        return nurseCount;
    }

    public int urgentQueueCount() {
        return urgentAlerts.count();
    }

    public int routineQueueCount() {
        return routineAlerts.count();
    }

    public void updateAlerts(int time) {
        for (int i = 0; i < patientCount; i++) {
            patients[i].generateAlerts(time, this);
        }
    }

    public void storeAlert(Alert alert) {
        if (alert.getSeverity() == Severity.URGENT) {
            urgentAlerts.add(alert);
        }
        else {
            routineAlerts.add(alert);
        }
    }

    public Alert getNextAlert() {
        if (!urgentAlerts.isEmpty()) {
            return urgentAlerts.remove();
        }

        return routineAlerts.remove();
    }

    public void updateNurses(int time) {
        for (int i = 0; i < nurseCount; i++) {
            Alert done = nurses[i].finishIfReady(time);

            if (done != null) {
                completedAlerts.add(done);
                System.out.println("Resolved by " + nurses[i].getId() + ": " + done);
            }

            if (nurses[i].isAvailable(time)) {
                Alert next = getNextAlert();

                if (next != null) {
                    nurses[i].takeAlert(next, time);
                }
            }
        }
    }
}