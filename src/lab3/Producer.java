package lab3;

public class Producer implements Runnable{
	private EventBarrier myEventBarrier;

	public Producer(EventBarrier eb){
		myEventBarrier = eb;
	}	
	public void run() {

		//myEventBarrier.arrive();

		myEventBarrier.raise();

		//myEventBarrier.complete(); //Not sure about this complete

	}
}