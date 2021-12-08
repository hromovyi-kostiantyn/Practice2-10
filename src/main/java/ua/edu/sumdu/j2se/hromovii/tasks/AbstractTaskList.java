package ua.edu.sumdu.j2se.hromovii.tasks;


public abstract class AbstractTaskList implements Iterable<Task> {
    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    public abstract int hashCode();

    public abstract String toString();

    public abstract boolean equals(Object o);

    public abstract Task getTask(int index);

    public abstract int size();

    public AbstractTaskList incoming(int from, int to) {
        AbstractTaskList list;
        if (this.getClass().getSimpleName().equals("ArrayTaskList")) {
            list = new ArrayTaskList();
        } else {
            list = new LinkedTaskList();
        }
        for (int i = 0; i < size(); i++) {
            Task task = getTask(i);
            if (task.isActive()) {
                if (task.getStartTime() > from && task.getEndTime() <= to) {
                    list.add(task);
                }
            }
        }
        return list;
    }
}
