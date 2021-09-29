package duke.task;

public abstract class Task {

    protected String description;
    protected boolean isDone;
    public static String GAP = " | ";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean isDone){
        this.isDone = isDone;
    }

    public String getDone() {
        if (isDone) {
            return "X";
        } else {
            return " ";
        }
    }

    public int getStorageFormatStatus() {
        if (isDone) {
            return 1;
        } else {
            return 0;
        }
    }

    public String toString() {
        return "[" + getDone() + "]" + description;
    }

    public abstract String getStorageFormat();
}

