import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;


public class Demo {
	private int numMachines, numTasks;
	private List<Machine> machines;
	private List<Integer> tasks;
	private String fileName;
	
	public Demo() {
		super();
		fileName = "src/input.txt";
		readFromFile();
	}
	
	public void readFromFile() {
		FileReader reader = null;
		Scanner in = null;
		try {
			reader = new FileReader(fileName);
			in = new Scanner(reader);
		} catch(FileNotFoundException e) {
			System.out.println("Could not find the file.");
			System.exit(1);
		}
		machines = new ArrayList<Machine>();
		tasks = new ArrayList<Integer>();
		numTasks = Integer.parseInt(in.nextLine());
		numMachines = Integer.parseInt(in.nextLine());
		String[] temp = in.nextLine().split(" ");
		if(temp.length == numTasks) {
			for(int i = 0; i < numTasks; i++) {
				tasks.add(Integer.parseInt(temp[i]));
			}
		}
		else {
			System.out.println("Mismatch on number of tasks.");
			System.exit(1);
		}
		temp = in.nextLine().split(" ");
		if(temp.length == numMachines) {
			for(int i = 0; i < numMachines; i++) {
				machines.add(new Machine(Integer.parseInt(temp[i])));
			}
		}
		else {
			System.out.println("Mismatch on number of machines.");
			System.exit(1);
		}
		Collections.sort(tasks);
		Collections.sort(machines);
		Collections.reverse(tasks);
	}
	
	public void optimize() {
		Algorithms.binaryAssign(machines, tasks);
	}
	
	public void printOutput() {
		for(Machine m : machines) {
			System.out.println(m);
		}
	}

	public static void main(String[] args) {
		Demo demo = new Demo();
		demo.optimize();
		demo.printOutput();
	}

}
