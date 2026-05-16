import java.util.Random;

public class Simulation {
    private static Random random = new Random();

    private Hospital hospital;
    private int patientCount;
    private int simulationMinutes;

    public Simulation() {
        hospital = null;
        patientCount = 10;
        simulationMinutes = 60;
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
        hospital = new Hospital(patientCount);

        for (int i = 0; i < patientCount; i++) {
            hospital.addPatient(Patient.create());
        }

        System.out.println("Hospital setup complete.");
        System.out.println("Patients: " + hospital.getPatientCount());
    }

    public void run() {
        for (int time = 0; time < simulationMinutes; time++) {
            if (time % 10 == 0) {
                System.out.println("Simulation time: " + time);
            }
        }
    }

    public void process() {
        System.out.println("Simulation finished.");
    }
}