import java.io.*;
import java.util.*;


public class Demo {
	private int numMachines, numTasks;
	private List<Machine> machines, inOrderMachines;
	private List<Integer> tasks;
	private String fileName;
	
	public Demo() {
		super();
		fileName = "ourInput.txt";
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
		inOrderMachines = new ArrayList<Machine>();
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
				inOrderMachines.add(new Machine(Integer.parseInt(temp[i])));
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

	public static void main(String[] args) {
		Demo demo = new Demo();
		demo.createInput();
		int max = demo.optimize();
		demo.printOutput();
		System.out.println(max);
	}

}
