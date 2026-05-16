public class AlertQueue {
    private Alert[] alerts;
    private int front;
    private int back;
    private int count;

    public AlertQueue(int size) {
        alerts = new Alert[size];
        front = 0;
        back = 0;
        count = 0;
    }

    public void add(Alert alert) {
        if (count < alerts.length) {
            alerts[back] = alert;
            back++;

            if (back == alerts.length) {
                back = 0;
            }

            count++;
        }
    }

    public Alert remove() {
        if (count == 0) {
            return null;
        }

        Alert alert = alerts[front];
        alerts[front] = null;
        front++;

        if (front == alerts.length) {
            front = 0;
        }

        count--;

        return alert;
    }

    public Alert peek() {
        if (count == 0) {
            return null;
        }

        return alerts[front];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int count() {
        return count;
    }
}