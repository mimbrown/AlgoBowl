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
		Machine maxMachine = machines.get(machines.size() - 1);
		Machine nextMachine = machines.get(machines.size() - 2);
		int count = 0;
		while(count < machines.size() - 1 && maxMachine.compareTo(nextMachine) > 0) {
			normalize(machines.get(count), maxMachine);
			count++;
		}
	}

	public static void normalize(Machine a, Machine b) {
		Machine min = a;
		Machine max = b;
		if(max.compareTo(min) < 0) {
			Machine temp = min;
			min = max;
			max = temp;
		}
		//System.out.println(min.getProcessTime());
		//System.out.println(max.getProcessTime());
		Double maxTime = max.getProcessTime();
		List<Task> minTasks = min.getTasks();
		List<Task> maxTasks = max.getTasks();
		Task currentMin, currentMax;
		Double proposal;
		for(int i = 0; i < minTasks.size(); i++) {
			currentMin = minTasks.get(i);
			proposal = min.getProcessTime() - ((double)currentMin.value)/min.getSpeed();
			for(int j = 0; j < maxTasks.size(); j++) {
				currentMax = maxTasks.get(j);
				if(proposal + ((double)currentMax.value)/min.getSpeed() < maxTime
						&& currentMin.value < currentMax.value) {
					boolean test = minTasks.remove(currentMin);
					//System.out.println(test);
					minTasks.add(i, currentMax);
					min.calcProcessTime();
					maxTasks.remove(currentMax);
					maxTasks.add(j, currentMin);
					max.calcProcessTime();
					currentMin = currentMax;
					proposal = min.getProcessTime() - currentMin.value/min.getSpeed();
					maxTime = max(min.getProcessTime(), max.getProcessTime());
				}
			}
		}
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
