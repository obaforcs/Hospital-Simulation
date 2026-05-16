import java.util.Random;

public class Simulation {
    private static Random random = new Random();

    private Hospital hospital;
    private int patientCount;
    private int simulationMinutes;
    private int nurseCount;
    private int urgentResolveTime;
    private int routineResolveTime;

    public Simulation() {
        hospital = null;
        patientCount = 10;
        simulationMinutes = 60;
        nurseCount = 3;
        urgentResolveTime = 8;
        routineResolveTime = 20;
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
        hospital = new Hospital(patientCount, nurseCount);

        for (int i = 0; i < patientCount; i++) {
            hospital.addPatient(Patient.create());
        }

        for (int i = 0; i < nurseCount; i++) {
            hospital.addNurse(new Nurse("Nurse " + (i + 1), urgentResolveTime, routineResolveTime));
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
    }

    public void process() {
        System.out.println("Simulation finished.");
    }
}