package lab3;

import java.util.ArrayList;
import java.util.List;

public class Building extends AbstractBuilding{
	private EventBarrier[] upCalls; 
	private EventBarrier[] downCalls; 
	private EventBarrier[][] elevatorCalls;
	List<Elevator> evs;
	Elevator ev;
	public int totalRiders; 

	public Building(int numFloors, int numElevators) {
		super(numFloors, numElevators);
		totalRiders = 2;
		evs = new ArrayList<Elevator>();
		//upCalls = new EventBarrier[numFloors];
		//downCalls = new EventBarrier[numFloors];
		//for(int i=0; i< numFloors; i++){
		//	upCalls[i] = new EventBarrier();
		//	downCalls[i] = new EventBarrier();
		//}	
	}

	@Override
	public AbstractElevator CallUp(int fromFloor) {
	// fix this to actually return
		System.out.println("calling Up on floor " + fromFloor);
		upCalls[fromFloor].arrive(); 
		System.out.println("exiting Up");
		return findElevator(fromFloor);
	}

	@Override
	public AbstractElevator CallDown(int fromFloor) {
		// fix this to actually return
		System.out.println("calling down on floor " + fromFloor);
		downCalls[fromFloor].arrive();
		return findElevator(fromFloor);
	}
	
	
	
	public Elevator findElevator(int floor){
		Elevator ret = null;
		int closestDist = numFloors*2 + 1;
		for(Elevator e:evs){
			if(e.getCurrentFloor() < floor){
				if(e.isGoingUp()){
					if(floor - e.getCurrentFloor() < closestDist){
						closestDist = floor - e.getCurrentFloor();
						ret = e;
					}
				}
				else{
					if(floor - e.getCurrentFloor() + 2*e.getCurrentFloor() < closestDist){
						closestDist = floor - e.getCurrentFloor() + 2*e.getCurrentFloor();
						ret = e;
					}
				}
			}
			else if(e.getCurrentFloor() == floor){
				return e;
			}
			else{
				if(!e.isGoingUp()){
					if(e.getCurrentFloor() - floor < closestDist){
						closestDist = e.getCurrentFloor() - floor;
						ret = e;
					}
				}
				else{
					if(e.getCurrentFloor() - floor + 2*e.getCurrentFloor() < closestDist){
						closestDist = e.getCurrentFloor() - floor + 2*e.getCurrentFloor();
						ret = e;
					}
				}
			}
		}
		return ret;
	}
	
	public void setEventBarriers(EventBarrier[] a, EventBarrier[] b, EventBarrier[][] c){
		upCalls = a;
		downCalls = b;
		elevatorCalls = c;
	}
	
	public synchronized void addFinishedRider(){
		totalRiders--;
		if(totalRiders == 0){
			for(Elevator ev:evs){
				ev.tellNoRidersLeft();
			}
		}
	}
	
	public void addElevator(Elevator e){
		evs.add(e);
	}

}
