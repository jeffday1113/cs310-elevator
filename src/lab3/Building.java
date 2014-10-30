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
		elevator = new Elevator(numFloors,0, 400);		
	}

	@Override
	public AbstractElevator CallUp(int fromFloor) {
	// fix this to actually return
		System.out.println("calling Up");
		upCalls[fromFloor-1].arrive(); 
		System.out.println("exiting Up");
		return elevator;
	}

	@Override
	public AbstractElevator CallDown(int fromFloor) {
		// fix this to actually return
		System.out.println("calling down");
		downCalls[fromFloor-1].arrive();
		return elevator;
	}

}
