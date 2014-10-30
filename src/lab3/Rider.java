package lab3;

public class Rider extends Thread{
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
			Main.writer.write((Thread.currentThread().getName() + "R" + id +
	                "pushes D" + originFloor));
			myElevator = myBuilding.CallUp(originFloor);
			
		} else {
			Main.writer.write((Thread.currentThread().getName() + "R" + id +
	                "pushes D" + originFloor));
			myElevator = myBuilding.CallDown(originFloor);
			
		}
		myElevator.Enter();
		
		myElevator.RequestFloor(requestedFloor);
		myElevator.Exit();
	
	}
	
}
