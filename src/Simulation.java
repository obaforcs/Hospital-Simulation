import java.util.Random;

public class Simulation {
    private static Random random = new Random();

    private Hospital hospital;
    private int patientCount;
    private int simulationMinutes;
    private int nurseCount;
    private int urgentResolveTime;
    private int routineResolveTime;
    private boolean telemedicine;

    public Simulation() {
        hospital = null;
        patientCount = 10;
        simulationMinutes = 60;
        nurseCount = 3;
        urgentResolveTime = 8;
        routineResolveTime = 20;
        telemedicine = true;
    }

    public static int randomInt(int max) {
        return random.nextInt(max);
    }

    public static double randomDouble() {
        return random.nextDouble();
    }

    public static boolean chance(int percent) {
        return random.nextInt(100) < percent;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setup() {
        hospital = new Hospital(patientCount, nurseCount + 1);

        for (int i = 0; i < patientCount; i++) {
            hospital.addPatient(Patient.create());
        }

        for (int i = 0; i < nurseCount; i++) {
            hospital.addNurse(new Nurse("Nurse " + (i + 1), urgentResolveTime, routineResolveTime));
        }

        if (telemedicine) {
            hospital.addNurse(new Nurse("Telemedicine Nurse", urgentResolveTime + 2, routineResolveTime / 2));
        }

        System.out.println("Hospital setup complete.");
        System.out.println("Patients: " + hospital.getPatientCount());
    }

    public void run() {
        for (int time = 0; time < simulationMinutes; time++) {
            hospital.updateAlerts(time);
            hospital.updateNurses(time);

            if (time % 10 == 0) {
                System.out.println("Simulation time: " + time
                        + " urgentQueue=" + hospital.urgentQueueCount()
                        + " routineQueue=" + hospital.routineQueueCount());
            }
        }

        for (int time = simulationMinutes; time < simulationMinutes + 120; time++) {
            hospital.updateNurses(time);
        }
    }

    public void process() {
        AlertQueue completed = hospital.getCompletedAlerts();

        int totalAlerts = 0;
        int urgentAlerts = 0;
        int routineAlerts = 0;

        int totalTime = 0;
        int urgentTime = 0;
        int routineTime = 0;
        int maxTime = 0;

        while (!completed.isEmpty()) {
            Alert alert = completed.remove();
            int time = alert.timeToResolve();

            totalAlerts++;
            totalTime += time;

            if (time > maxTime) {
                maxTime = time;
            }

            if (alert.getSeverity() == Severity.URGENT) {
                urgentAlerts++;
                urgentTime += time;
            }
            else {
                routineAlerts++;
                routineTime += time;
            }
        }

        System.out.println();
        System.out.println("Simulation Results");
        System.out.println("Patients: " + patientCount);
        System.out.println("Nurses: " + hospital.getNurseCount());
        System.out.println("Telemedicine: " + telemedicine);
        System.out.println("Total completed alerts: " + totalAlerts);
        System.out.println("Urgent completed alerts: " + urgentAlerts);
        System.out.println("Routine completed alerts: " + routineAlerts);
        System.out.println("Maximum wait time: " + maxTime);

        if (totalAlerts > 0) {
            System.out.println("Average wait time: " + ((double) totalTime / totalAlerts));
        }

        if (urgentAlerts > 0) {
            System.out.println("Average urgent wait time: " + ((double) urgentTime / urgentAlerts));
        }

        if (routineAlerts > 0) {
            System.out.println("Average routine wait time: " + ((double) routineTime / routineAlerts));
        }
    }
}