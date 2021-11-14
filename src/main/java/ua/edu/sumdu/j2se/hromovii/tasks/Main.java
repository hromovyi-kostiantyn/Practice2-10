package ua.edu.sumdu.j2se.hromovii.tasks;

public class Main {

	public static void main(String[] args)
	{
		ArrayTaskList actual = new ArrayTaskList(5);
		System.out.println(actual.size());
		Task task1 = new Task("name", 10, true);
		Task task2 = new Task("name2", 20,true);
		Task task3 = new Task("name3", 30,true);

		actual.add(task1);
		actual.add(task2);
		actual.add(task3);
		actual.add(task1);
		actual.add(task1);
		System.out.println(actual.size());
		actual.remove(task1);
		System.out.println(actual.size());
		actual.getTask(2);
		actual.incoming(0,100);

	}
}
