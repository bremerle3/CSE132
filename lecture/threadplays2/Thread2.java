package threadplays2;

public class Thread2 extends Thread {
	
	// this is the payload of this thread...
	//  when this thread runs, the run method does something
	@Override
	public void run() {
		try {
			System.out.println("Thread 2 about to nap");
			Thread.sleep(3000);  // 3 seconds
			System.out.println("Thread 2 woke up");
		} catch (InterruptedException e) {
			// FIXME Auto-generated catch block
			e.printStackTrace();
		} // 2 seconds
		
	}

}
