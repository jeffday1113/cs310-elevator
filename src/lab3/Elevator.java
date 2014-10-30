package lab3;

import java.util.ArrayList;
import java.util.List;

public class Elevator extends AbstractElevator {
	private int current; 
	private List<Rider> riderList;

	public Elevator(int numFloors, int elevatorId, int maxOccupancyThreshold) {
		super(numFloors, elevatorId, maxOccupancyThreshold);

		riderList = new ArrayList<Rider>();
	}

	@Override
	public void OpenDoors() {
		Main.writer.println("E ?" + "on F" +"?"+ "opens");
		//raise();
		//then close doors
		ClosedDoors();
	}

	@Override
	public void ClosedDoors() {
		Main.writer.println("E ?" + "on F" +"?"+ "closes");
		//get next request either from outside or inside
	}

	@Override
	public void VisitFloor(int floor) {
		if(current<floor){
			Main.writer.println("E " +"?" + "moves up to floor" + (floor+1));
		}
		if(current > floor){
			Main.writer.println("E " +"?" + "moves down to floor" + (floor+1));
		}
		current = floor;
		OpenDoors();
	}

	@Override
	public boolean Enter() {
		return true;
	}

	@Override
	public void Exit() {
		Thread riderThread = Thread.currentThread();
		Rider rider = (Rider) riderThread;
		if(current == rider.requestedFloor-1){
			Main.writer.println("R" +rider.id + "exits E" +"?" + "on F"+ current);
			//complete
			riderList.remove(rider);
		}

	}

	@Override
	public void RequestFloor(int floor) {
		// TODO Auto-generated method stub
		// the rider arrive at the floor, call arrive

	}

}
