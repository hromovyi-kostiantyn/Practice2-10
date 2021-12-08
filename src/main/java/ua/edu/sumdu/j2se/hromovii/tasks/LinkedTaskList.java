package ua.edu.sumdu.j2se.hromovii.tasks;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedTaskList extends AbstractTaskList implements Cloneable {
    static class Node {
        Task task;
        Node next;

        public Node(Task task) {
            this.task = task;
        }
    }

    private Node first;
    private Node last;
    private int size;

    @Override
    public void add(Task task) {
        Node newNode = new Node(task);
        if (isEmpty()) {
            first = last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    @Override
    public boolean remove(Task task) {
        if (isHere(task)) {
            int index = getIndexByTask(task);
            Objects.checkIndex(index, size);
            if (index == 0) {
                first = first.next;
                if (first == null) {
                    last = null;
                }
            } else {
                Node prev = getNodeByIndex(index - 1);
                prev.next = prev.next.next;
                if (index == size - 1) {
                    last = prev;
                }
            }
            size--;
            return true;
        } else {
            return false;
        }
    }

    private void remove(int index) {
        Objects.checkIndex(index, size);
        if (index == 0) {
            first = first.next;
            if (first == null) {
                last = null;
            }
        } else {
            Node prev = getNodeByIndex(index - 1);
            prev.next = prev.next.next;
            if (index == size - 1) {
                last = prev;
            }
        }
        size--;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public Task getTask(int index) {
        Objects.checkIndex(index, size);
        if (index < 0 || isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        return getNodeByIndex(index).task;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    private Node getNodeByIndex(int index) {
        Node current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    private int getIndexByTask(Task task) {
        Node current = first;
        for (int i = 0; i < size; i++) {
            if (current.task.equals(task)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    private boolean isHere(Task task) {
        Node current = first;
        for (int i = 0; i < size; i++) {
            if (current.task.equals(task)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public String toString() {
        return "LinkedTaskList{" +
                "first=" + first +
                ", last=" + last +
                ", size=" + size +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof LinkedTaskList))
            return false;
        Iterator<Task> it1 = iterator();
        Iterator<Task> it2 = ((LinkedTaskList) o).iterator();
        while (it1.hasNext() && it2.hasNext()) {
            Task o1 = it1.next();
            Object o2 = it2.next();
            if (!(o1 == null ? o2 == null : o1.equals(o2)))
                return false;
        }
        return !(it1.hasNext() || it2.hasNext());
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        for (Task e : this)
            hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
        return hashCode;
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
                    LinkedTaskList.this.remove(prevElem);
                    if (prevElem < current)
                        current--;
                    prevElem = -1;
                } catch (IndexOutOfBoundsException e) {
                    throw new ConcurrentModificationException();
                }
            }

        };
    }

    @Override
    public Object clone() {
        LinkedTaskList clone = superClone();

        clone.first = clone.last = null;
        clone.size = 0;

        for (Node x = first; x != null; x = x.next)
            clone.add(x.task);

        return clone;
    }

    private LinkedTaskList superClone() {
        try {
            return (LinkedTaskList) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }
}

