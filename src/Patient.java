import java.util.UUID;

public class Patient {
    private String id;
    private Device[] devices;
    private int deviceCount;

    public Patient(String id) {
        this.id = id;
        devices = new Device[10];
        deviceCount = 0;
    }

    public static Patient create() {
        Patient patient = new Patient(UUID.randomUUID().toString());

        patient.addDevice(new TemperatureDevice());
        patient.addDevice(new HeartRateDevice());
        patient.addDevice(new BloodPressureDevice());
        patient.addDevice(new CallBellDevice());

        return patient;
    }

    public String getId() {
        return id;
    }

    public int getDeviceCount() {
        return deviceCount;
    }

    public Device getDevice(int index) {
        if (index < 0 || index >= deviceCount) {
            return null;
        }

        return devices[index];
    }

    public void addDevice(Device device) {
        if (device != null && deviceCount < devices.length) {
            devices[deviceCount] = device;
            deviceCount++;
        }
    }

    public void generateAlerts(int time) {
        for (int i = 0; i < deviceCount; i++) {
            Observation observation = devices[i].getObservation(time, this);

            if (observation.dangerous()) {
                Alert alert = new Alert(observation, time, observation.severity());
                System.out.println(alert);
            }
        }
    }
}