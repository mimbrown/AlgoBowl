import java.util.*;


public class Algorithms {
	
	public static void binaryAssign(List<Machine> machines, List<Task> tasks) {
		if(machines.size() == 0 || tasks.size() == 0) return;
		if(machines.size() == 1 || tasks.size() == 0) {
			machines.get(0).addTasks(tasks);
			return;
		}
		binaryAssign(machines.subList(0, machines.size()/2), tasks.subList(0, tasks.size()/2));
		binaryAssign(machines.subList(machines.size()/2, machines.size()),
				     tasks.subList(tasks.size()/2, tasks.size()));
	}
	
	public static Double getMax(List<Machine> machines) {
		return Collections.max(machines).getProcessTime();
	}
	
	public static void altAlgo(List<Machine> machines, List<Task> tasks)
	{
		Double min = Double.MAX_VALUE;
		Machine temp = new Machine(1);
		while(!tasks.isEmpty())
		{
			min = Double.MAX_VALUE;
			for(int i=0; i<machines.size(); i++)
			{
				if((machines.get(i).getProcessTime() + tasks.get(0).value/machines.get(i).getSpeed()) < min)
				{
					
					temp = machines.get(i);
					min = (double) (machines.get(i).getProcessTime() + tasks.get(0).value/machines.get(i).getSpeed());
				}
			
			}
			temp.addTasks(tasks.subList(0, 1));
			tasks.remove(0);
		}
	}
	
	public static void normMachines(List<Machine> machines) {
		for(int i = 0; i < machines.size() / 2; i++) {
			normalize(machines.get(i), machines.get(machines.size() - i - 1));
		}
	}
	
	public static void normalize(Machine a, Machine b) {
		List<Task> tasks = new ArrayList<Task>();
		tasks.addAll(a.getTasks());
		tasks.addAll(b.getTasks());
		Collections.sort(tasks);
		a.clearTasks();
		b.clearTasks();
		Machine max = a;
		Machine min = b;
		if(max.compareTo(min) < 0) {
			Machine temp = max;
			max = min;
			min = temp;
		}
		List<Task> tasksB = new ArrayList<Task>();
		int numTasksB = 0;
		double current = processTime(tasks, min.getSpeed());
		double next;
		while(numTasksB < tasks.size()) {
			tasksB.add(tasks.get(0));
			tasks.remove(0);
			next = max(processTime(tasks, min.getSpeed()), processTime(tasksB, max.getSpeed()));
			if(next < current) {
				current = next;
				numTasksB++;
			}
			else {
				tasks.add(tasksB.get(tasksB.size() - 1));
				tasksB.remove(tasksB.get(tasksB.size() - 1));
				break;
			}
		}
		min.addTasks(tasks);
		max.addTasks(tasksB);
	}
	
	public static double processTime(List<Task> tasks, int speed) {
		double cost = 0;
		for(Task t : tasks) {
			cost += t.value;
		}
		return cost / speed;
	}
	
	public static double max(double a, double b) {
		if(a > b) return a;
		else return b;
	}

}
