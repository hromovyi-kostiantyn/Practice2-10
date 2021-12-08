package ua.edu.sumdu.j2se.hromovii.tasks;

import java.util.Objects;

public class Task implements Cloneable {
    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean repeated;
    private boolean active;

    public Task(String title, int time) {
        if (time < 0 || title == null) {
            throw new IllegalArgumentException();
        }
        this.title = title;
        this.time = time;
        this.repeated = false;
    }

    public Task(String title, int start, int end, int interval) {
        if (start < 0 || end < 0 || interval < 0 || title == null) {
            throw new IllegalArgumentException();
        }
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.repeated = true;
    }

    public void setTime(int time) {
        if (time < 0) {
            throw new IllegalArgumentException();
        }
        this.time = time;
        this.repeated = false;
    }

    public void setTime(int start, int end, int interval) {
        if (start < 0 || end < 0 || interval < 0) {
            throw new IllegalArgumentException();
        }
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.repeated = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null) {
            throw new IllegalArgumentException();
        }
        this.title = title;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getTime() {
        if (repeated) {
            return start;
        } else {
            return time;
        }
    }

    public int getStartTime() {
        if (repeated) {
            return start;
        } else {
            return time;
        }
    }

    public int getEndTime() {
        if (repeated) {
            return end;
        } else {
            return time;
        }
    }

    public int getRepeatInterval() {
        if (repeated) {
            return interval;
        } else {
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
                } else {
                    int temp;
                    temp = start;
                    while (current >= temp) {
                        temp = temp + interval;
                    }
                    if (temp <= end) {
                        return temp;
                    } else {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return time == task.time && start == task.start && end == task.end && interval == task.interval
                && repeated == task.repeated && active == task.active && title.equals(task.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, time, start, end, interval, repeated, active);
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", time=" + time +
                ", start=" + start +
                ", end=" + end +
                ", interval=" + interval +
                ", repeated=" + repeated +
                ", active=" + active +
                '}';
    }

    @Override
    public Task clone() {
        try {
            return (Task) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}