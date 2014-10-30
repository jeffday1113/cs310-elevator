package lab3;



public class Elevator extends AbstractElevator {
	private int current; 
	EventBarrier[] downCalls;
	EventBarrier[] upCalls;
	
	private EventBarrier currEntryBarrier;



	public Elevator(int numFloors, int elevatorId, int maxOccupancyThreshold, EventBarrier[] up, EventBarrier[] down) {
		super(numFloors, elevatorId, maxOccupancyThreshold);

		downCalls = down;
		upCalls = up;

		
	}

	@Override
	public void OpenDoors() {
		System.out.println("opening doors");
		Main.writer.println("E ?" + "on F" +"?"+ "opens");
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
			currEntryBarrier = upCalls[current];
		}
		if(current > floor){
			System.out.println("visiting floor " + floor );
			currEntryBarrier = downCalls[current];
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
			upCalls[current].complete();
	}

	@Override
	public void RequestFloor(int floor) {
		upCalls[floor-1].arrive();
		System.out.println("Requesting from floor " + (floor));
		
	}

}
