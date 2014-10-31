package lab3;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Main {
	

	/*public static  PrintWriter writer ;
	
	public static void main(String[] args) {
		
		try {
			writer = new PrintWriter("Elevator.log", "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(args.length==0) {
			Parser.open();
		} 
		else if(args.length > 1) {
			writer.println("Too many arguments");
		} 
		else if(args[0].equals("p1")) //Test cases for Event Barrier are "hard baked" into test class
		{
			writer.println("Event Barrier:");
		}
		else if(args[0].equals("p2")){ 
		
			Parser.open();
		}
		writer.close();
	}*/
	public static void main(String args[]){
		int floors = 2;
		int elevators = 2;
		Building b = new Building(floors, elevators);
		Elevator e = new Elevator(floors, 0, 1);
		Elevator f = new Elevator(floors, 1, 1);
		b.addElevator(e);
		b.addElevator(f);
		
		EventBarrier[] upCalls; 
		EventBarrier[] downCalls; 
		EventBarrier[][] elevatorCalls;
		upCalls = new EventBarrier[floors];
		downCalls = new EventBarrier[floors];
		elevatorCalls = new EventBarrier[elevators][floors];
		for(int i=0; i< floors; i++){
			upCalls[i] = new EventBarrier();
			downCalls[i] = new EventBarrier();
		}	
		for(int i = 0; i < elevators; i++){
			for(int j = 0; j < floors; j++){
				elevatorCalls[i][j] = new EventBarrier();
			}
		}
		b.setEventBarriers(upCalls, downCalls, elevatorCalls);
		e.setEventBarriers(upCalls, downCalls, elevatorCalls);
		f.setEventBarriers(upCalls, downCalls, elevatorCalls);
		
		Rider r = new Rider(1, 0, 1, b);
		Rider rr = new Rider(2, 1, 0, b);
		Thread hi = new Thread(e);
		hi.start();
		Thread hii = new Thread(f);
		hii.start();
		Thread hi2 = new Thread(r);
		hi2.start();
		Thread hi3 = new Thread(rr);
		hi3.start();
	}
	
}
