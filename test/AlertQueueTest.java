import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AlertQueueTest {
    @Test
    public void testEmptyQueue() {
        AlertQueue queue = new AlertQueue(10);

        assertTrue(queue.isEmpty());
        assertEquals(0, queue.count());
        assertNull(queue.remove());
    }

    @Test
    public void testAddAndRemove() {
        Patient patient = new Patient("p1");
        Observation observation = new TemperatureObservation(0, patient, 104.0);
        Alert alert = new Alert(observation, 0, Severity.URGENT);

        AlertQueue queue = new AlertQueue(10);
        queue.add(alert);

        assertFalse(queue.isEmpty());
        assertEquals(1, queue.count());
        assertEquals(alert, queue.remove());
        assertTrue(queue.isEmpty());
    }
}