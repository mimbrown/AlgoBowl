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
	
	public static int getMax(List<Machine> machines) {
		return Collections.max(machines).getProcessTime();
	}

}
