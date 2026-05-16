import java.util.UUID;

public class Patient {
    private String id;

    public Patient(String id) {
        this.id = id;
    }

    public static Patient create() {
        return new Patient(UUID.randomUUID().toString());
    }

    public String getId() {
        return id;
    }
}