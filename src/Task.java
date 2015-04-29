
public class Task implements Comparable {
	public Integer value;
	public int id;
	
	public Task(Integer value, int id) {
		super();
		this.value = value;
		this.id = id;
	}

	@Override
	public int compareTo(Object o) {
		if(!(o instanceof Task)) return 0;
		return value.compareTo(((Task)o).value);
	}

}
