package threadplay1;

public class Thread1 extends Thread { // need to say that

	
	@Override
	public void run() { // this is what happens concurrently when it's time
		System.out.println("Thread 1 feels drowsy");
		System.out.println("Thread 1 About to sleep for 2 seconds");
		//  sleep
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// FIXME Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Thread 1 woke up");
		
	}
}
