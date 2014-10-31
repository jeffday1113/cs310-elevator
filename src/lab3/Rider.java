package lab3;

public class Rider implements Runnable{
	/*This should be a thread for each rider who calls the elevator
	 * 
	 */
	public int requestedFloor;
	public int originFloor;
	private Building myBuilding;
	public int id;
	
	public Rider(int ID, int requested, int current, Building building){
		myBuilding = building;
		requestedFloor = requested;
		originFloor = current;
		id = ID;
	}

	public void run() {
		
		AbstractElevator myElevator;
		if(requestedFloor > originFloor) { 
		//	Main.writer.println(("R" + id +
		//			" pushes D" + originFloor));
			System.out.println("call elevator going up from " + originFloor + " to " + requestedFloor);
			myElevator = myBuilding.CallUp(originFloor);
			//we never get an elevator back

		} else {
			//Main.writer.write("R" + id +
		//			" pushes D" + originFloor);
			System.out.println("call elevator going down from " + originFloor + " to " + requestedFloor);
			myElevator = myBuilding.CallDown(originFloor);
		}

		myElevator.Enter();
		myElevator.RequestFloor(requestedFloor);
		myElevator.Exit();
		myBuilding.addFinishedRider();

	}
	
	

}
