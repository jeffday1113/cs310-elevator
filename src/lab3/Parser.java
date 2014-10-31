package lab3;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class Parser {
	private static int floors;
	private static int elevators;
	@SuppressWarnings("unused")
	private static int riders;
	@SuppressWarnings("unused")
	private static int capacity;

	public static void open() {
		JFileChooser inputChooser = new JFileChooser(".");
		int retval = JFileChooser.ERROR_OPTION;
		while(retval == JFileChooser.ERROR_OPTION) {
			retval = inputChooser.showOpenDialog(null);
			if (retval == JFileChooser.APPROVE_OPTION) {
				File file = inputChooser.getSelectedFile();
				try {
					build(new Scanner(file));
				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(null,
							"File not found" + file.getName(),
							"IO error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	public static void build(Scanner s) {
		String[] parameters = s.nextLine().split(" ");
		floors = Integer.parseInt(parameters[0]);
		elevators = Integer.parseInt(parameters[1]);
		riders = Integer.parseInt(parameters[2]);
		capacity = Integer.parseInt(parameters[3]);

		Building sb = new Building(floors, elevators);
		List<Elevator> myElevators = new ArrayList<Elevator>();
		
		List<Rider> myRiders = new ArrayList<Rider>();
		for(int i = 0; i < elevators; i++) {
			myElevators.add(new Elevator(floors, 0, elevators));
		}
		while(s.hasNextLine()) {
			String[] query = s.nextLine().split(" ");
			int riderID = Integer.parseInt(query[0]);
			int startFloor = Integer.parseInt(query[1]);
			int endFloor = Integer.parseInt(query[2]);
			Rider r = new Rider(riderID, endFloor, startFloor, sb);
			myRiders.add(r);
		}
		for(int i = 0; i < elevators; i++) {
			Thread p = new Thread(myElevators.get(i));
			 p.start();
		}
		for(int i = 0; i<myRiders.size(); i++){
			Thread p = new Thread(myRiders.get(i));
			p.start();
		}
		
		
	}

}