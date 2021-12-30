package ua.edu.sumdu.j2se.hromovii.tasks;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public abstract class AbstractTaskList implements Iterable<Task>, Serializable {
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
        return Arrays.stream(elements);
    }
}
