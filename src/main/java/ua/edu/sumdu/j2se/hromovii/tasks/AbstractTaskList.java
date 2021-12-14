package ua.edu.sumdu.j2se.hromovii.tasks;

import java.util.stream.Stream;

public abstract class AbstractTaskList implements Iterable<Task> {
    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    public abstract int hashCode();

    public abstract String toString();

    public abstract boolean equals(Object o);

    public abstract Task getTask(int index);

    public abstract int size();

    public Stream<Task> getStream() {
        Task[] elements = new Task[size()];
        for (int i = 0;i < size();i++) {
            elements[i] = getTask(i);
        }
        return Stream.of(elements);
    }

    public final AbstractTaskList incoming(int from, int to) {
        AbstractTaskList list;
        if (this.getClass().getSimpleName().equals("ArrayTaskList")) {
            list = new ArrayTaskList();
        } else {
            list = new LinkedTaskList();
        }
        Stream<Task> taskStream = getStream();
        taskStream
                .filter(task -> task.nextTimeAfter(from) != -1 && task.nextTimeAfter(to) == -1)
                .forEach(list::add);
        return list;
    }

}
