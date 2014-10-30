package lab3;



public class Elevator extends AbstractElevator implements Runnable {
	private int current; 
	EventBarrier[] downCalls;
	EventBarrier[] upCalls;
	private boolean moreRiders;
	private boolean goingUp;
	private EventBarrier currEntryBarrier;



	public Elevator(int numFloors, int elevatorId, int maxOccupancyThreshold) {
		super(numFloors, elevatorId, maxOccupancyThreshold);
		upCalls = new EventBarrier[numFloors];
		downCalls = new EventBarrier[numFloors];
		for(int i=0; i< numFloors; i++){
			upCalls[i] = new EventBarrier();
			downCalls[i] = new EventBarrier();
		}
		moreRiders = false;
		current = 1;
		goingUp = true;
	}

	@Override
	public void OpenDoors() {
		System.out.println("opening doors");
		Main.writer.println("E" + "?" + "on F" +"?"+ "opens");
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
		currEntryBarrier.complete();
	}

	@Override
	public void RequestFloor(int floor) {
		System.out.println("Requesting from floor " + (floor));
		downCalls[floor-1].arrive();
		System.out.println(currEntryBarrier.waiters());
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(moreRiders){
			if(goingUp){
				current++;
				if(current == numFloors){
					goingUp = false;
				}
			}
			else{
				current--;
				if(current == 1){
					goingUp = true;
				}
			}
			VisitFloor(current);
		}
		
	}

	
}
