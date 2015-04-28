import java.util.*;


public class Machine implements Comparable {
	private Integer speed;
	private List<Integer> tasks;
	private Integer processTime;
	
	public Machine(Integer speed) {
		super();
		this.speed = speed;
		tasks = new ArrayList<Integer>();
		processTime = 0;
	}
	
	public void addTasks(List<Integer> tasks) {
		this.tasks.addAll(tasks);
		calcProcessTime();
	}
	
	public void calcProcessTime() {
		processTime = 0;
		for(Integer i : tasks) {
			processTime += i;
		}
		processTime /= speed;
	}
	
	public String taskString() {
		String toReturn = "";
		for(Integer i : tasks) {
			toReturn += i + ", ";
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
