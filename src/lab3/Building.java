package lab3;

public class Building extends AbstractBuilding{
	private EventBarrier[] upCalls; 
	private EventBarrier[] downCalls; 
	private EventBarrier[][] elevatorCalls;
	Elevator ev;
	public int totalRiders; 

	public Building(int numFloors, int numElevators) {
		super(numFloors, numElevators);
		totalRiders = 1;
		//upCalls = new EventBarrier[numFloors];
		//downCalls = new EventBarrier[numFloors];
		//for(int i=0; i< numFloors; i++){
		//	upCalls[i] = new EventBarrier();
		//	downCalls[i] = new EventBarrier();
		//}	
	}

	@Override
	public AbstractElevator CallUp(int fromFloor) {
	// fix this to actually return
		System.out.println("calling Up on floor " + fromFloor);
		upCalls[fromFloor].arrive(); 
		System.out.println("exiting Up");
		return findElevator();
	}

	@Override
	public AbstractElevator CallDown(int fromFloor) {
		// fix this to actually return
		System.out.println("calling down on floor " + fromFloor);
		downCalls[fromFloor].arrive();
		return findElevator();
	}
	
	public Elevator findElevator(){
		return ev;
	}
	
	public void setEventBarriers(EventBarrier[] a, EventBarrier[] b, EventBarrier[][] c){
		upCalls = a;
		downCalls = b;
		elevatorCalls = c;
	}
	
	public void setElevator(Elevator e){
		ev = e;
	}
	
	public synchronized void addFinishedRider(){
		totalRiders--;
		if(totalRiders == 0){
			ev.tellNoRidersLeft();
		}
	}

}
