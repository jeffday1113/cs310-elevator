package lab3;

public class EventBarrier extends AbstractEventBarrier{
	private int count = 0;
	private boolean isInSignaledState;
	public EventBarrier() {
		isInSignaledState = false; //keep the state of the current progress
		count = 0;
	}
	
	@Override
	public synchronized void arrive() {
		//System.out.println("Thread: " + Thread.currentThread().getId() + " arrived at event barrier");
		count++;
		
		if(isInSignaledState){
		//	System.out.println("uhi");
			return;
		}
		while(!isInSignaledState){ //loop before leaping
			try {
			//	System.out.println(count);
			//	System.out.println("Thread: " + Thread.currentThread().getId() + " waiting");
				
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public synchronized void raise() {
		//System.out.println("called raise");
		isInSignaledState = true;	
		notifyAll();
		while(isInSignaledState){ //loop before leaping
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			isInSignaledState = count>0;
		}
		notifyAll();
	}

	@Override
	public synchronized void complete() {
		//System.out.println("Thread: " + Thread.currentThread().getId() + " has been completed");
		count--; //decrement from the waiters
		isInSignaledState = !(count==0);
		notifyAll();
		while(isInSignaledState){
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	@Override
	public int waiters() {
		//System.out.println("# of waiting threads is: " + count);
		return count;
	}

}
