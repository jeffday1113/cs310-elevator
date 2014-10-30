package lab3;

public class Building extends AbstractBuilding{
	public Elevator elevator;
	private EventBarrier[] iridersList;  //inside requests

	public Building(int numFloors, int numElevators) {
		super(numFloors, numElevators);
		iridersList = new EventBarrier[numFloors];
		for(int i=0; i< numFloors; i++){
			iridersList[i] = new EventBarrier();
		}
		elevator = new Elevator(numFloors,0, 400);
	}

	@Override
	public AbstractElevator CallUp(int fromFloor) {
		System.out.println("Am calling up");
		//elevator.RequestFloor(fromFloor);		
		iridersList[fromFloor-1].arrive();		
		return elevator;
	}

	@Override
	public AbstractElevator CallDown(int fromFloor) {
		System.out.println("Am calling down");
	//	elevator.RequestFloor(fromFloor);
		iridersList[fromFloor-1].arrive();
		return elevator;
	}

}
