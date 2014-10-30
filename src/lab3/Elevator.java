package lab3;

import java.util.ArrayList;


public class Elevator extends AbstractElevator {
	private int current; 
	EventBarrier[] iridersList;
	EventBarrier[] oridersList;
	
	private EventBarrier currEntryBarrier;

	ArrayList<Rider> Ordered_Outside_RequestList;
	static final int UP=0;
	static final int DOWN = 1;
	static int NOT_MOVING = 2;
	int direction = UP;

	public Elevator(int numFloors, int elevatorId, int maxOccupancyThreshold) {
		super(numFloors, elevatorId, maxOccupancyThreshold);

		iridersList = new EventBarrier[numFloors];
		oridersList = new EventBarrier[numFloors];

		for(int i=0; i< numFloors; i++){
			iridersList[i] = new EventBarrier();
			oridersList[i] = new EventBarrier();
		}
	}

	@Override
	public void OpenDoors() {
		System.out.println("opening doors");
		Main.writer.println("E ?" + "on F" +"?"+ "opens");
		 oridersList[current].raise();  //exit barrier
         currEntryBarrier.raise();
		ClosedDoors();
	}

	@Override
	public void ClosedDoors() {
		Main.writer.println("E ?" + "on F" +"?"+ "closes");
		
		//get next rider
	}

	@Override
	public void VisitFloor(int floor) {
		if(current<floor){
			System.out.println("visiting floor " + floor );
			Main.writer.println("E " +"?" + "moves up to floor" + (floor));
			currEntryBarrier = iridersList[current];
		}
		if(current > floor){
			System.out.println("visiting floor " + floor );
			currEntryBarrier = oridersList[current];
			Main.writer.println("E " +"?" + "moves down to floor" + (floor));
		}
		current = floor;
		OpenDoors();
	}

	@Override
	public synchronized boolean Enter() {
		if(current >0){
		currEntryBarrier.complete();
		return true;
		}else{
		return false;
		}
	}

	@Override
	public void Exit() {
		System.out.println("Exit");
		Thread riderThread = Thread.currentThread();
		Rider rider = (Rider) riderThread;
		if(current == rider.requestedFloor-1){
			Main.writer.println("R" +rider.id + "exits E" +"?" + "on F"+ current);
			oridersList[current].complete();
		}

	}

	@Override
	public void RequestFloor(int floor) {
		System.out.println("Requesting from floor " + (floor));
		oridersList[floor-1].arrive();
		
	}

}
