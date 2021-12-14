package ua.edu.sumdu.j2se.hromovii.tasks;

import java.util.Arrays;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        ArrayTaskList actual = new ArrayTaskList(20);
        System.out.println("ARRAY TASK LIST");
        System.out.println(actual.size());
        Task task1 = new Task("name1", 10);
        Task task2 = new Task("name2", 20);
        Task task3 = new Task("name3", 30);
        Task task4 = new Task("name4", 40);
        Task task5 = new Task("name5", 50);
        Task task6 = new Task("name6", 60);


        actual.add(task1);
        actual.add(task2);
        actual.add(task3);
        actual.add(task1);
        actual.add(task1);
        System.out.println(actual.size());
        actual.remove(task1);
        System.out.println(actual.size());
        actual.getTask(2);
        actual.incoming(0, 100);
        System.out.println("LINKED TASK LIST");

        LinkedTaskList list1 = new LinkedTaskList();
        System.out.println(list1.size());
        list1.add(task1);
        list1.add(task1);
        System.out.println(list1.size());
        list1.remove(task1);
        System.out.println(list1.size());

        TaskListFactory.createTaskList(ListTypes.types.ARRAY);

        LinkedTaskList actual1 = new LinkedTaskList();
        System.out.println(actual.incoming(0, 100));
        System.out.println(actual1.incoming(0, 200));

        System.out.println(TaskListFactory.createTaskList(ListTypes.types.ARRAY));


        ArrayTaskList arrayList = new ArrayTaskList();
        arrayList.add(task1);
        arrayList.add(task2);
        arrayList.add(task3);
        arrayList.add(task4);
        arrayList.add(task5);
        arrayList.add(task6);
        System.out.println("--------------------------");
        for (Task task : arrayList) {
            System.out.println(task);
        }
        ArrayTaskList a1 = (ArrayTaskList) arrayList.clone();
        System.out.println(a1==arrayList);

        System.out.println(arrayList.getStream().count());

    }
}
