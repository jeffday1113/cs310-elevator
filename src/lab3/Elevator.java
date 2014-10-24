package lab3;

public class Elevator extends AbstractElevator {

	public Elevator(int numFloors, int elevatorId, int maxOccupancyThreshold) {
		super(numFloors, elevatorId, maxOccupancyThreshold);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void OpenDoors() {
		// TODO Auto-generated method stub
		//raise();
		//then close doors
	}

	@Override
	public void ClosedDoors() {
		// TODO Auto-generated method stub
		//get next thread
	}

	@Override
	public void VisitFloor(int floor) {
		// TODO Auto-generated method stub
		//open doors()
	}

	@Override
	public boolean Enter() {
		// TODO Auto-generated method stub
		//complete 
		return false;
	}

	@Override
	public void Exit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void RequestFloor(int floor) {
		// TODO Auto-generated method stub
		//arrive
		
	}

}
