package ua.edu.sumdu.j2se.hromovii.tasks;

import java.util.Objects;

public class LinkedTaskList {
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
    public boolean remove(Task task) {
        if (isHere(task)) {
            int index = getIndexByTask(task);
            Objects.checkIndex(index, size);
            if (index == 0) {
                first = first.next;
                if (first == null) {
                    last = null;
                }
            }
            else {
                Node prev = getNodeByIndex(index - 1);
                prev.next = prev.next.next;
                if (index == size - 1) {
                    last = prev;
                }
            }
            size--;
            return true;
        }
        else {
            return false;
        }
    }
    public int size() {
        return size;
    }
    public Task getTask(int index) {
        Objects.checkIndex(index, size);
        if (isEmpty()) {
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
    public LinkedTaskList incoming(int from, int to) {
        LinkedTaskList list = new LinkedTaskList();
        for (int i = 0; i < size;i++) {
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
