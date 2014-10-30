package lab3;

public class Consumer implements Runnable{
	private EventBarrier myEventBarrier;

	public Consumer(EventBarrier eb){
		myEventBarrier = eb;
	}	
	public void run() {

		myEventBarrier.arrive();

		//myEventBarrier.raise();

		myEventBarrier.complete(); //Not sure about this complete

	}
}

