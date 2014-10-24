package lab3;

public class EventBarrier extends AbstractEventBarrier{
	private int count = 0;
	private boolean var = true;
	public EventBarrier() {
		var = false;
		count = 0;
	}
	
	@Override
	public synchronized void arrive() {
		count++;
		while(!var){
			try {
				
			wait();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	@Override
	public synchronized void raise() {
		var = !(count==0);		
		while(var){
			notifyAll();
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public synchronized void complete() {
		count--;
		var = !(count==0);
		notifyAll();
	}

	@Override
	public int waiters() {
		return count;
	}

}
