package lab3;

public class EventBarrierTestMain {

	public static void main(String[] args) {
		
		EventBarrierRunnable j = new EventBarrierRunnable(new EventBarrier());
		EventBarrierRunnable k = new EventBarrierRunnable(new EventBarrier());
		/*I put this loop in trying to test for multiple threads
		 * help me in making this make sense
		 */
		for(int i = 0; i < 6; i++){  
			Thread t = new Thread(j);
			Thread x = new Thread(k);
			
			if(i<=3){  //Here threads run concurrently
				t.start();
				x.start();
			}else{
				x.start();  //single thread running

			}
				
		}
		
		
	}
	

}
