import java.io.*;
import java.util.*;


public class Demo {
	private int numMachines, numTasks;
	private List<Machine> machines, inOrderMachines;
	private List<Task> tasks;
	private String fileName;
	
	public Demo() {
		super();
		fileName = "ourInput.txt";
		readFromFile(fileName);
	}
	
	public void readFromFile(String file) {
		FileReader reader = null;
		Scanner in = null;
		try {
			reader = new FileReader(file);
			in = new Scanner(reader);
		} catch(FileNotFoundException e) {
			System.out.println("Could not find the file.");
			System.exit(1);
		}
		machines = new ArrayList<Machine>();
		inOrderMachines = new ArrayList<Machine>();
		tasks = new ArrayList<Task>();
		numTasks = Integer.parseInt(in.nextLine());
		numMachines = Integer.parseInt(in.nextLine());
		String[] temp = in.nextLine().split(" ");
		if(temp.length == numTasks) {
			for(int i = 0; i < numTasks; i++) {
				tasks.add(new Task(Integer.parseInt(temp[i]), i+1));
			}
		}
		else {
			System.out.println("Mismatch on number of tasks.");
			System.exit(1);
		}
		temp = in.nextLine().split(" ");
		if(temp.length == numMachines) {
			for(int i = 0; i < numMachines; i++) {
				Machine m = new Machine(Integer.parseInt(temp[i]));
				machines.add(m);
				inOrderMachines.add(m);
			}
		}
		else {
			System.out.println("Mismatch on number of machines.");
			System.exit(1);
		}
	}
	
	public int optimize() {
		Collections.sort(tasks);
		Collections.sort(machines);
		Collections.reverse(tasks);
		Algorithms.binaryAssign(machines, tasks);
		return Algorithms.getMax(machines);
	}
	
	public void printOutput() {
		for(Machine m : machines) {
			System.out.println(m);
		}
	}
	
	public void createInput() {
		int mach = 50;
		int tsks = 1000;
		File file = null;
		PrintWriter out = null;
		try {
			file = new File("ourInput.txt");
			out = new PrintWriter(file);
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
		Random rand = new Random();
		out.println(tsks);
		out.println(mach);
		for(int i = 0; i < tsks - 1; i++) {
			out.print(rand.nextInt(10000)+1);
			out.print(" ");
		}
		out.print(rand.nextInt(10000)+1);
		out.print("\n");
		for(int i = 0; i < mach - 1; i++) {
			out.print(rand.nextInt(20)+1);
			out.print(" ");
		}
		out.print(rand.nextInt(20)+1);
		out.close();
	}
	
	public void output() {
		File file = null;
		PrintWriter out = null;
		try {
			file = new File("ourOutput.txt");
			out = new PrintWriter(file);
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
		int max = optimize();
		for(Machine m : inOrderMachines) {
			for(Task t : m.getTasks()) {
				out.print(t.id);
				out.print(" ");
			}
			out.print("\n");
		}
		out.println(max);
		out.close();
	}
	
	public boolean validate(String file) {
		readFromFile(fileName);
		FileReader reader = null;
		Scanner in = null;
		try {
			reader = new FileReader(file);
			in = new Scanner(reader);
		} catch(FileNotFoundException e) {
			System.out.println("Could not find the file.");
			System.exit(1);
		}
		double maxCost = -1;
		for(int i = 0; i < numMachines; i++) {
			String[] temp = in.nextLine().split(" ");
			double cost = 0;
			for(int j = 0; j < temp.length; j++) {
				int index = Integer.parseInt(temp[j]) - 1;
				if(tasks.get(index) != null) {
					cost += tasks.get(index).value;
					tasks.set(index, null);
				}
				else return false;
			}
			cost /= machines.get(i).getSpeed();
			if(cost > maxCost) maxCost = cost;
		}
		int theirMax = Integer.parseInt(in.nextLine());
		in.close();
		if(Math.abs(theirMax - maxCost) > 0.0001) return false;
		for(Task t : tasks) {
			if(t != null) return false;
		}
		return true;
	}

	public static void main(String[] args) {
		Demo demo = new Demo();
		//demo.createInput();
		demo.output();
		boolean isWorking = demo.validate("ourOutput.txt");
		System.out.println(isWorking);
	}

}
