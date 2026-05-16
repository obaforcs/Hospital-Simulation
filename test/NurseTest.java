import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NurseTest {
    @Test
    public void testNurseTakesAlert() {
        Patient patient = new Patient("p1");
        Observation observation = new TemperatureObservation(0, patient, 104.0);
        Alert alert = new Alert(observation, 0, Severity.URGENT);
        Nurse nurse = new Nurse("n1", 8, 20);

        assertTrue(nurse.isAvailable(0));

        nurse.takeAlert(alert, 0);

        assertFalse(nurse.isAvailable(1));
        assertTrue(nurse.isAvailable(8));
        assertEquals(8, alert.getResolvedTime());
    }
}