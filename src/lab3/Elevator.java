package lab3;



public class Elevator extends AbstractElevator implements Runnable {
	private int current; 
	private int numFloor;
	EventBarrier[] downCalls;
	EventBarrier[] upCalls;
	EventBarrier[][] elevatorCalls;
	private boolean doorOpen;
	private boolean moreRiders;
	private boolean goingUp;
	private EventBarrier currEntryBarrier;
	private int elevatorID;
	private int floorGoingTo;



	public Elevator(int numFloors, int elevatorId, int maxOccupancyThreshold) {
		super(numFloors, elevatorId, maxOccupancyThreshold);
		System.out.println("Number of floors " + numFloors);
		upCalls = new EventBarrier[numFloors];
		downCalls = new EventBarrier[numFloors];
		for(int i=0; i< numFloors; i++){
			upCalls[i] = new EventBarrier();
			downCalls[i] = new EventBarrier();
		}
		elevatorID = elevatorId;
		numFloor = numFloors;
		moreRiders = true;
		current = 0;
		goingUp = true;
		doorOpen = false;
	}

	@Override
	public void OpenDoors() {
		System.out.println("opening doors");
		doorOpen = true;
		//Main.writer.println("E" + "?" + "on F" +"?"+ "opens");
		currEntryBarrier.raise();
		elevatorCalls[elevatorID][current].raise();
	}

	@Override
	public void ClosedDoors() {
		//Main.writer.println("E ?" + "on F" +"?"+ "closes");
		System.out.println("closing doors");
		doorOpen = false;
		//get next rider
	}

	@Override
	public void VisitFloor(int floor) {
		if(goingUp){
			System.out.println("visiting floor " + floor );
		//	Main.writer.println("E " +"?" + "moves up to floor" + (floor));
			currEntryBarrier = upCalls[current];
		}
		else{
			System.out.println("visiting floor " + floor );
			currEntryBarrier = downCalls[current];
		//	Main.writer.println("E " +"?" + "moves down to floor" + (floor));
		}
		current = floor;
		if(currEntryBarrier.waiters() > 0 || current == floorGoingTo){
			OpenDoors();
			ClosedDoors();
		}
	}

	@Override
	public synchronized boolean Enter() {
		System.out.println("entering");
		currEntryBarrier.complete();
		return true;
		
	}

	@Override
	public void Exit() {	
		elevatorCalls[elevatorID][current].complete();
	}

	@Override
	public void RequestFloor(int floor) {
		System.out.println("Requesting floor " + (floor));
		floorGoingTo = floor;
		elevatorCalls[elevatorID][floor].arrive();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(moreRiders){
			if(goingUp){
				current++;
				if(current == numFloor-1){
					goingUp = false;
				}
			}
			else{
				current--;
				if(current == 0){
					goingUp = true;
				}
			}
			System.out.println("elevator on floor " + current);
			VisitFloor(current);
		}
		
	}
	
	public void setEventBarriers(EventBarrier[] a, EventBarrier[] b, EventBarrier[][] c){
		upCalls = a;
		downCalls = b;
		elevatorCalls = c;
	}

	public void tellNoRidersLeft(){
		moreRiders = false;
	}
	
}
