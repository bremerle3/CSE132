package threadplays2;

public class Thread1 extends Thread {
	
	// this is the payload of this thread...
	//  when this thread runs, the run method does something
	@Override
	public void run() {
		try {
			System.out.println("Thread 1 about to nap");
			Thread.sleep(2000);  // 2 seconds
			System.out.println("Thread 1 woke up");
		} catch (InterruptedException e) {
			// FIXME Auto-generated catch block
			e.printStackTrace();
		} // 2 seconds
		
	}

}
