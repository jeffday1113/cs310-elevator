package lab3;

public class Building extends AbstractBuilding{
	private Elevator elevator;
	//rider list 
	// elevator state i.e. going up or going down
	
	public Building(int numFloors, int numElevators) {
		super(numFloors, numElevators);
		// TODO Auto-generated constructor stub
		//construct elevator with numFloors, elevatorId, maxOccupancyThreshold
	}

	@Override
	public AbstractElevator CallUp(int fromFloor) {
		// TODO Auto-generated method stub
		//wait for elevator
		//loop before leaping
		//arrive
		return elevator;
	}

	@Override
	public AbstractElevator CallDown(int fromFloor) {
		// TODO Auto-generated method stub
		//wait for elevator
		//loop before leaping
		//arrive
		return elevator;
	}

}
