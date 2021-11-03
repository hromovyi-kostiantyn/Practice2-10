package ua.edu.sumdu.j2se.hromovii.tasks;

public class Task {
    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean repeated;
    private boolean active;

    public Task(String title, int time) {
        this.title = title;
        this.time = time;
        this.repeated = false;
    }
    public Task(String title, int start, int end, int interval) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.repeated = true;
    }
    public void setTime(int time) {
        this.time = time;
        this.repeated = false;
    }
    public void setTime(int start, int end, int interval) {
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.repeated = true;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public int getTime() {
        if (repeated) {
            return start;
        }
        else {
            return time;
        }
    }
    public int getStartTime() {
        if (repeated) {
            return start;
        }
        else {
            return time;
        }
    }
    public int getEndTime() {
        if (repeated) {
            return end;
        }
        else {
            return time;
        }
    }
    public int getRepeatInterval() {
        if (repeated) {
            return interval;
        }
        else {
            return 0;
        }
    }
    public boolean isRepeated() {
        return repeated;
    }
    public int nextTimeAfter(int current) {
    if (active) {
        if (repeated) {
            if (start > current) {
                return start;
            } else  {
                int temp;
                temp = start;
                while (current >= temp) {
                    temp = temp + interval;
                }
                if (temp <= end) {
                    return temp;
                }
                else {
                    return -1;
                }
            }
        } else {
            if (current < time) {
                return time;
            } else {
                return -1;
            }
        }
    } else {
            return -1;
        }
    }
}
