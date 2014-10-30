package lab3;

public class Building extends AbstractBuilding{
	public Elevator elevator;
	private EventBarrier[] upCalls; 
	private EventBarrier[] downCalls; 
	
	public Building(int numFloors, int numElevators) {
		super(numFloors, numElevators);
		
		upCalls = new EventBarrier[numFloors];
		downCalls = new EventBarrier[numFloors];
		for(int i=0; i< numFloors; i++){
			upCalls[i] = new EventBarrier();
			downCalls[i] = new EventBarrier();
		}
		elevator = new Elevator(numFloors,0, 400, upCalls, downCalls);
	}

	@Override
	public AbstractElevator CallUp(int fromFloor) {
		upCalls[fromFloor-1].arrive();
		return elevator;
	}

	@Override
	public AbstractElevator CallDown(int fromFloor) {
		downCalls[fromFloor-1].arrive();
		return elevator;
	}

}
