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
			Main.writer.println(("R" + id +
					" pushes D" + originFloor));
			System.out.println("about to call elevator");
			myElevator = myBuilding.CallUp(originFloor);
			//we never get an elevator back
			System.out.println("return an up elevator");

		} else {
			Main.writer.write("R" + id +
					" pushes D" + originFloor);
			myElevator = myBuilding.CallDown(originFloor);
			System.out.println("return a down elevator");
		}

		myElevator.Enter();
		myElevator.RequestFloor(requestedFloor);
		myElevator.Exit();

	}

}
