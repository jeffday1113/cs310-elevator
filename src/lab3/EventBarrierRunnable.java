package lab3;

public class EventBarrierRunnable implements Runnable{
	private EventBarrier myEventBarrier;

	public EventBarrierRunnable(EventBarrier eb){
		myEventBarrier = eb;
	}	
	public void run() {

		myEventBarrier.arrive();

		myEventBarrier.raise();

		myEventBarrier.complete(); //Not sure about this complete

	}
}

