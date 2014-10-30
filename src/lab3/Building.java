package lab3;

public class Building extends AbstractBuilding{
	public Elevator elevator;
	private EventBarrier[] ridersList;
	//rider list 
	// elevator state i.e. going up or going down
	
	public Building(int numFloors, int numElevators) {
		super(numFloors, numElevators);
		ridersList = new EventBarrier[numFloors];
		for(int i=0; i< numFloors; i++){
			ridersList[i] = new EventBarrier();
			
		}
		elevator = new Elevator(numFloors,0, 1);
		// TODO Auto-generated constructor stub
		//construct elevator with numFloors, elevatorId, maxOccupancyThreshold
	}

	@Override
	public AbstractElevator CallUp(int fromFloor) {
		// TODO Auto-generated method stub
		//wait for elevator
		//loop before leaping
		//arrive
		EventBarrier eb = ridersList[fromFloor-1];
		elevator.RequestFloor(fromFloor);
		//System.out.println("Rider has called for the elevator from floor " + fromFloor);
		eb.arrive();
		
		return elevator;
	}

	@Override
	public AbstractElevator CallDown(int fromFloor) {
		EventBarrier eb = ridersList[fromFloor-1];
		elevator.RequestFloor(fromFloor);
		System.out.println("Rider has called for the elevator from floor " + fromFloor);
		eb.arrive();
		return elevator;
	}

}
