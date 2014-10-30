package lab3;

public class Rider extends Thread implements Runnable{
 /*This should be a thread for each rider who calls the elevator
  * 
  */
	private int requestedFloor;
	private int originFloor;
	private Building myBuilding;
	public Rider(int ID, int requested, int current, Building building){
		myBuilding = building;
		requestedFloor = requested;
		originFloor = current;
	}
	
	public void run() {
		AbstractElevator myElevator;
		if(requestedFloor > originFloor) { 
			myElevator = myBuilding.CallUp(originFloor);
		} else {
			myElevator = myBuilding.CallDown(originFloor);
		}
		myElevator.Enter();
		myElevator.RequestFloor(requestedFloor);
		myElevator.Exit();

	}
	
}
