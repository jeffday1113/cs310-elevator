package lab3;

public class EventBarrier extends AbstractEventBarrier{
	private int count = 0;
	private boolean state;
	public EventBarrier() {
		state = false; //keep the state of the current progress
		count = 0;
	}
	
	@Override
	public synchronized void arrive() {
		System.out.println("Thread: " + Thread.currentThread().getId() + " arrived at event barrier");
		count++;
		state = !(count==0);
		while(!state){ //loop before leaping
			try {
				System.out.println(count);
				System.out.println("Thread: " + Thread.currentThread().getId() + " waiting?");
			this.wait();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public synchronized void raise() {
		System.out.println("Thread: " + Thread.currentThread().getId() + " is being raised");
		state = !(count==0);		
		while(state){ //loop before leaping
			notifyAll();
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public synchronized void complete() {
		System.out.println("Thread: " + Thread.currentThread().getId() + " has been completed");
		count--; //decrement from the waiters
		state = !(count==0);
		notifyAll();
	}

	@Override
	public int waiters() {
		System.out.println("# of waiting threads is: " + count);
		return count;
	}

}
