import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ObservationTest {
    @Test
    public void testTemperatureDangerous() {
        Patient patient = new Patient("p1");
        TemperatureObservation temp = new TemperatureObservation(0, patient, 104.0);

        assertTrue(temp.dangerous());
        assertEquals(Severity.URGENT, temp.severity());
    }

    @Test
    public void testBloodPressureNormal() {
        Patient patient = new Patient("p1");
        BloodPressureObservation bp = new BloodPressureObservation(0, patient, 120, 80);

        assertFalse(bp.dangerous());
        assertEquals("blood pressure: 120/80", bp.data());
    }
}