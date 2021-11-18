package ua.edu.sumdu.j2se.hromovii.tasks;

public class ArrayTaskList {
    private Task[] tasks;
    private int size;

    public ArrayTaskList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        } else {
            tasks = new Task[capacity];
        }
    }

    public ArrayTaskList() {
        int DEFAULT_CAPACITY = 10;
        tasks = new Task[DEFAULT_CAPACITY];
    }

    public int size() {
        return size;
    }

    public void add(Task task) {
        if (tasks.length == size) {
            int increase = (int) Math.floor(tasks.length * 1.5) + 1;
            Task[] temp = new Task[increase];
            System.arraycopy(tasks, 0, temp, 0, size);
            tasks = temp;
        }
        tasks[size] = task;
        size++;
    }
    public boolean remove(Task task) {
        if (isHere(task)) {
            int pos = index(task);
            Task[] temp = tasks;
            tasks = new Task[temp.length];
            System.arraycopy(temp, 0, tasks, 0, pos);
            System.arraycopy(temp, pos + 1, tasks, pos, temp.length - pos - 1);
            size--;
            return true;
        } else {
            return false;
        }
    }
    public Task getTask(int index) {
        return tasks[index];
    }
    private int index(Task task) {
        if (task == null) {
            return -1;
        }
        for (int i = 0; i < size; i++) {
            if (task.equals(tasks[i]))
                return i;
        }
        return -1;
    }

    private boolean isHere(Task task) {
        for (Task x : tasks) {
            if (x.equals(task)) {
                return true;
            }
        }
        return false;
    }
    public ArrayTaskList incoming(int from, int to) {
        ArrayTaskList list1 = new ArrayTaskList();
        for (Task x: tasks
        ) {
            if (x != null) {
                if (x.isActive()) {
                    if (x.getStartTime() > from && x.getEndTime() <= to) {
                        list1.add(x);
                    }
                }
            }
        }
        return list1;
    }
}
