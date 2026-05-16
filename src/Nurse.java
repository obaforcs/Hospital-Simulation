public class Nurse {
    private String id;
    private Alert currentAlert;
    private int availableTime;
    private int urgentResolveTime;
    private int routineResolveTime;

    public Nurse(String id, int urgentResolveTime, int routineResolveTime) {
        this.id = id;
        currentAlert = null;
        availableTime = 0;
        this.urgentResolveTime = urgentResolveTime;
        this.routineResolveTime = routineResolveTime;
    }

    public String getId() {
        return id;
    }

    public boolean isAvailable(int time) {
        return currentAlert == null || time >= availableTime;
    }

    public Alert finishIfReady(int time) {
        if (currentAlert != null && time >= availableTime) {
            Alert finished = currentAlert;
            currentAlert = null;
            return finished;
        }

        return null;
    }

    public void takeAlert(Alert alert, int time) {
        currentAlert = alert;

        if (alert.getSeverity() == Severity.URGENT) {
            availableTime = time + urgentResolveTime;
        }
        else {
            availableTime = time + routineResolveTime;
        }

        alert.resolve(availableTime);
    }
}