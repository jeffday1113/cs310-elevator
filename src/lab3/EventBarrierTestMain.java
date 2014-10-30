package lab3;

public class EventBarrierTestMain {
	public EventBarrierTestMain(){
		String[] args = null;
		main(args);
	}
	public static void main(String[] args) {
		EventBarrier bar = new EventBarrier();
		Consumer j = new Consumer(bar);
		/*I put this loop in trying to test for multiple threads
		 * help me in making this make sense
		 */
		Producer p = new Producer(bar);
		for(int i = 0; i < 6; i++){  
			Thread t = new Thread(j);
		
			if(i<=3){  //Here threads run concurrently
				t.start();
				System.out.println(System.lineSeparator() + "printing i" + i);
			}else{

			}
				
		}
		System.out.println("linedradjrrkt");
		//bar.raise();
		try {
			Thread.sleep(1);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Thread e = new Thread(p);
		e.start();
		
		
	}
	

}
