import java.util.*;


public class Machine implements Comparable {
	private Integer speed;
	private List<Task> tasks;
	private Double processTime;
	
	public Machine(Integer speed) {
		super();
		this.speed = speed;
		tasks = new ArrayList<Task>();
		processTime = 0.0;
	}
	
	public void addTasks(List<Task> tasks) {
		this.tasks.addAll(tasks);
		calcProcessTime();
	}
	
	public void addTask(Task t) {
		this.tasks.add(t);
		processTime += t.value/speed;
	}
	
	public void removeTask(int index) {
		processTime -= tasks.get(index).value/speed;
		this.tasks.remove(index);
	}
	
	public void replaceTask(Task thisT, Task otherT) {
		for(int i = 0; i < tasks.size() - 1; i++) {
			if(tasks.get(i).equals(thisT)) {
				//processTime = processTime + (otherT.value - thisT.value)/speed;
				tasks.remove(i);
				tasks.add(i, otherT);
				calcProcessTime();
				return;
			}
		}
	}
	
	public void removeTask(Task t) {
		for(Task i : tasks) {
			if(i.equals(t)) {
				processTime -= t.value/speed;
				tasks.remove(i);
				return;
			}
		}
	}
	
	public void calcProcessTime() {
		processTime = 0.0;
		for(Task i : tasks) {
			processTime += i.value;
		}
		processTime /= speed;
	}
	
	public Double getProcessTime() {
		return processTime;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void clearTasks() {
		tasks.clear();
	}
	
	public List<Task> getTasks() {
		return tasks;
	}
	
	public String taskString() {
		String toReturn = "";
		for(Task i : tasks) {
			toReturn += i.value + ", ";
		}
		return toReturn;
	}

	@Override
	public int compareTo(Object o) {
		if(!(o instanceof Machine)) return 0;
		if(!processTime.equals(((Machine)o).processTime)) {
			return processTime.compareTo(((Machine)o).processTime);
		}
		else return ((Machine)o).speed.compareTo(speed);
	}
	
	@Override
	public String toString() {
		return speed + ": Process Time = " + processTime + ", tasks: " + taskString();
	}

}
