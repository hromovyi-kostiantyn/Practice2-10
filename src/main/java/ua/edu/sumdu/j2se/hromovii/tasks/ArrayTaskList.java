package ua.edu.sumdu.j2se.hromovii.tasks;

import java.util.*;
import java.util.function.Consumer;

public class ArrayTaskList extends AbstractTaskList implements Cloneable {
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

    @Override
    public int size() {
        return size;
    }

    @Override
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

    @Override
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

    private void remove(int index) {
        if (index >= 0) {
            Task[] temp = tasks;
            tasks = new Task[temp.length];
            System.arraycopy(temp, 0, tasks, 0, index);
            System.arraycopy(temp, index + 1, tasks, index, temp.length - index - 1);
            size--;
        } else {
            throw new IllegalArgumentException();
        }
    }
    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(tasks);
        return result;
    }

    @Override
    public String toString() {
        return "ArrayTaskList{" +
                "tasks=" + Arrays.toString(tasks) +
                ", size=" + size +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArrayTaskList)) return false;
        ArrayTaskList that = (ArrayTaskList) o;
        return size == that.size && Arrays.equals(tasks, that.tasks);
    }

    @Override
    public Object clone() {
        try {
            ArrayTaskList result = (ArrayTaskList) super.clone();
            result.tasks = tasks.clone();
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public Task getTask(int index) {
        Objects.checkIndex(index, size);
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
            if (task.equals(x)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<Task> iterator() {
        return new Iterator<>() {
            int current;
            int prevElem = -1;

            @Override
            public boolean hasNext() {
                return current != size;
            }

            public Task next() {
                try {
                    int i = current;
                    Task next = getTask(i);
                    prevElem = i;
                    current = i + 1;
                    return next;
                } catch (IndexOutOfBoundsException e) {
                    throw new NoSuchElementException();
                }
            }

            public void remove() {
                if (prevElem < 0)
                    throw new IllegalStateException();

                try {
                    ArrayTaskList.this.remove(prevElem);
                    if (prevElem < current)
                        current--;
                    prevElem = -1;
                } catch (IndexOutOfBoundsException e) {
                    throw new ConcurrentModificationException();
                }
            }

        };
    }
}